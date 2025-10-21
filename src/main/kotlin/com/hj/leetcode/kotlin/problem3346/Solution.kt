package com.hj.leetcode.kotlin.problem3346

/**
 * LeetCode page: [3346. Maximum Frequency of an Element After Performing Operations I](https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-i/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun maxFrequency(
        nums: IntArray,
        k: Int,
        numOperations: Int,
    ): Int {
        val newNums = IntArray(nums.size + 1)
        nums.copyInto(newNums)
        // Extra number for the ease of boundary check
        newNums[newNums.lastIndex] = Int.MAX_VALUE
        newNums.sort()
        // Ensure the extra number does not affect the result
        check(newNums.last() - 2 * k > newNums[nums.lastIndex]) {
            "The extra number may lead to incorrect result"
        }

        // We greedily limit the target value to match nums[i]-k,
        // nums[i], and nums[i]+k, and use three sliding windows
        // to determine the corresponding frequencies.
        var i = 0
        var result = 0

        // Mid window contains all numbers in [nums[i]-k, nums[i]+k]
        var midStart = 0
        var midEnd = 0

        // Left window contains all numbers in [nums[i]-2k, nums[i]]
        var leftStart = 0

        // Right window contains all numbers in [nums[i], nums[i]+2k]
        var rightEnd = 0

        while (i < nums.size) {
            while (newNums[leftStart] + 2 * k < newNums[i]) {
                leftStart++
            }

            midStart = maxOf(midStart, leftStart)
            while (newNums[midStart] + k < newNums[i]) {
                midStart++
            }

            val oldI = i
            i++
            while (newNums[i] == newNums[oldI]) {
                i++
            }

            midEnd = maxOf(midEnd, i)
            while (newNums[midEnd] <= newNums[oldI] + k) {
                midEnd++
            }

            rightEnd = maxOf(rightEnd, midEnd)
            while (newNums[rightEnd] <= newNums[oldI] + 2 * k) {
                rightEnd++
            }

            result =
                maxOf(
                    result,
                    minOf(midEnd - midStart, i - oldI + numOperations),
                    minOf(i - leftStart, numOperations),
                    minOf(rightEnd - oldI, numOperations),
                )
        }
        return result
    }
}
