package com.hj.leetcode.kotlin.problem32

/**
 * LeetCode page: [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun longestValidParentheses(s: String): Int {
        val bestOpen = IntArray(s.length) { -1 } // not exist when bestOpen < 0
        var maxLen = 0
        for (i in s.indices) {
            if (s[i] == ')') {
                var j = i - 1
                // nesting
                while (0 <= j && s[j] != '(') {
                    j = bestOpen[j] - 1
                }
                // concatenation
                while (1 <= j && 0 <= bestOpen[j - 1]) {
                    j = bestOpen[j - 1]
                }

                bestOpen[i] = j
                if (0 <= j) {
                    maxLen = maxOf(maxLen, i - j + 1)
                }
            }
        }
        return maxLen
    }
}
