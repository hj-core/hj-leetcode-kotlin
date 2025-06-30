package com.hj.leetcode.kotlin.problem594

/**
 * LeetCode page: [594. Longest Harmonious Subsequence](https://leetcode.com/problems/longest-harmonious-subsequence/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun findLHS(nums: IntArray): Int {
        val freq = nums.asSequence().groupingBy { it }.eachCount()
        var result = 0
        for ((num, count) in freq) {
            freq[num + 1]?.let {
                result = maxOf(result, count + it)
            }
        }
        return result
    }
}
