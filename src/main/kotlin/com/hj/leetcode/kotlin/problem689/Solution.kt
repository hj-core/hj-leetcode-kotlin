package com.hj.leetcode.kotlin.problem689

/**
 * LeetCode page: [689. Maximum Sum of 3 Non-Overlapping Subarrays](https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of nums.
     */
    fun maxSumOfThreeSubarrays(
        nums: IntArray,
        k: Int,
    ): IntArray {
        val windowSums = windowSums(nums, k)
        var bestFirst = 0
        val bestThird = bestThird(windowSums, k) // bestThird[second]
        val result = intArrayOf(0, k, bestThird[k])
        var resultSum = result.sumOf { windowSums[it] }

        for (second in k + 1..nums.size - 2 * k) {
            if (windowSums[second - k] > windowSums[bestFirst]) {
                bestFirst = second - k
            }
            val sum = windowSums[bestFirst] + windowSums[second] + windowSums[bestThird[second]]
            if (sum > resultSum) {
                resultSum = sum
                result[0] = bestFirst
                result[1] = second
                result[2] = bestThird[second]
            }
        }
        return result
    }

    private fun windowSums(
        nums: IntArray,
        width: Int,
    ): IntArray {
        val result = IntArray(nums.size - width + 1)
        result[0] = (0..<width).sumOf { nums[it] }
        for (start in 1..<result.size) {
            result[start] = result[start - 1] - nums[start - 1] + nums[start + width - 1]
        }
        return result
    }

    private fun bestThird(
        windowSums: IntArray,
        k: Int,
    ): IntArray {
        val result = IntArray(windowSums.size - k)
        result[result.lastIndex] = windowSums.lastIndex
        for (i in result.lastIndex - 1 downTo k) {
            val newThird = i + k
            result[i] = if (windowSums[newThird] >= windowSums[result[i + 1]]) newThird else result[i + 1]
        }
        return result
    }
}
