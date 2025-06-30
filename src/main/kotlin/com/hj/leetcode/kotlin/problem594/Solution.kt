package com.hj.leetcode.kotlin.problem594

/**
 * LeetCode page: [594. Longest Harmonious Subsequence](https://leetcode.com/problems/longest-harmonious-subsequence/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun findLHS(nums: IntArray): Int {
        val freq = nums.groupBy { it }
        return freq.maxOf { (k, v) ->
            freq[k + 1]?.let { v.size + it.size } ?: 0
        }
    }
}
