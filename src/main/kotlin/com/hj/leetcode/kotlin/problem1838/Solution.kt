package com.hj.leetcode.kotlin.problem1838

/**
 * LeetCode page: [1838. Frequency of the Most Frequent Element](https://leetcode.com/problems/frequency-of-the-most-frequent-element/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of nums.
     */
    fun maxFrequency(
        nums: IntArray,
        k: Int,
    ): Int {
        val sortedNums = nums.sorted()
        var left = 0
        var windowSum = 0L
        // This version of sliding window algorithm focuses on the width.
        // The largest width is inherited during the sliding process.
        for (right in sortedNums.indices) {
            val num = sortedNums[right].toLong()
            windowSum += num

            if (windowSum + k < num * (right - left + 1)) {
                windowSum -= sortedNums[left]
                left++
            }
        }
        return sortedNums.size - left
    }
}
