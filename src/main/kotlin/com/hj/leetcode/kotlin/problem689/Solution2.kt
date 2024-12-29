package com.hj.leetcode.kotlin.problem689

/**
 * LeetCode page: [689. Maximum Sum of 3 Non-Overlapping Subarrays](https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun maxSumOfThreeSubarrays(
        nums: IntArray,
        k: Int,
    ): IntArray {
        val result = intArrayOf(0, k, 2 * k)
        val maxTwoSol = MaxSumOfTwoSubarrays(nums, k, k * 2)
        var thirdSum = (k * 2..<k * 3).sumOf { nums[it] } // Sum of the third window
        var resultSum = thirdSum + maxTwoSol.resultSum

        for (third in 2 * k + 1..nums.size - k) {
            thirdSum += nums[third + k - 1] - nums[third - 1]
            maxTwoSol.next()

            val threeSum = thirdSum + maxTwoSol.resultSum
            if (threeSum > resultSum) {
                resultSum = threeSum
                result[0] = maxTwoSol.result[0]
                result[1] = maxTwoSol.result[1]
                result[2] = third
            }
        }
        return result
    }

    class MaxSumOfTwoSubarrays(
        val nums: IntArray,
        val k: Int,
        beforeIndex: Int, // initial size of prefix
    ) {
        private var second = k // Current candidate of second
        private var secondSum = (k..<k * 2).sumOf { nums[it] } // Sum of the second window
        private var newFirst = 0 // New candidate of first after second advanced
        private var newFirstSum = (0..<k).sumOf { nums[it] } // Sum of the new first window
        private var bestFirst = newFirst // Best candidate of first
        private var bestFirstSum = newFirstSum // Sum of the best first window

        var result = intArrayOf(newFirst, second) // Starting indices of the two subarrays
        var resultSum = newFirstSum + secondSum // Sum of the two subarrays

        init {
            repeat(beforeIndex - 2 * k) {
                next()
            }
        }

        // Increase the size of prefix and update the states
        fun next() {
            secondSum += nums[second + k] - nums[second]
            second++

            newFirstSum += nums[newFirst + k] - nums[newFirst]
            newFirst++
            if (newFirstSum > bestFirstSum) {
                bestFirst = newFirst
                bestFirstSum = newFirstSum
            }

            val twoSum = secondSum + bestFirstSum
            if (twoSum > resultSum) {
                result[0] = bestFirst
                result[1] = second
                resultSum = twoSum
            }
        }
    }
}
