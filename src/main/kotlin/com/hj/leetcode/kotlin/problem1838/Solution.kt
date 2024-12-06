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

        for (right in sortedNums.indices) {
            val target = sortedNums[right].toLong()
            windowSum += target

            // The width of the largest window is inherited during sliding
            if (windowSum + k < target * (right - left + 1)) {
                windowSum -= sortedNums[left]
                left++
            }
        }
        return sortedNums.size - left
    }
}
