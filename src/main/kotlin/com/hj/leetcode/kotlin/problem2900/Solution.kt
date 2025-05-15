package com.hj.leetcode.kotlin.problem2900

/**
 * LeetCode page: [2900. Longest Unequal Adjacent Groups Subsequence I](https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/);
 */
class Solution {
    // Complexity:
    // Time O(L) and Auxiliary Space O(1) where L is the flattened length of words.
    fun getLongestSubsequence(
        words: Array<String>,
        groups: IntArray,
    ): List<String> =
        buildList {
            add(words[0])
            for (i in 1..<words.size) {
                if (groups[i] != groups[i - 1]) {
                    add(words[i])
                }
            }
        }
}
