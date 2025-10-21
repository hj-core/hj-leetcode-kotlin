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

        // We greedily limit the target value to match nums[i]-k
        // and nums[i], and use two sliding windows to determine
        // the corresponding frequencies.
        var i = 0
        var result = 0

        // Left window contains all numbers in [nums[i]-2k, nums[i]]
        var leftStart = 0

        // Mid window contains all numbers in [nums[i]-k, nums[i]+k]
        var midStart = 0
        var midEnd = 0

        while (i < nums.size) {
            while (2 * k < newNums[i] - newNums[leftStart]) {
                leftStart++
            }

            while (k < newNums[i] - newNums[midStart]) {
                midStart++
            }

            val oldI = i
            i++
            while (newNums[i] == newNums[oldI]) {
                i++
            }

            while (newNums[midEnd] - newNums[oldI] <= k) {
                midEnd++
            }

            result =
                maxOf(
                    result,
                    minOf(midEnd - midStart, i - oldI + numOperations),
                    minOf(i - leftStart, numOperations),
                )
        }
        return result
    }
}
