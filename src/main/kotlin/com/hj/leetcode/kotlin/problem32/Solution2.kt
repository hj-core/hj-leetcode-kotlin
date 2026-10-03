package com.hj.leetcode.kotlin.problem32

/**
 * LeetCode page: [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun longestValidParentheses(s: String): Int {
        var maxLen = 0

        var leftBound = -1
        var netOpen = 0
        for (i in s.indices) {
            netOpen += 1 - (s[i] - '(') * 2
            if (netOpen == 0) {
                maxLen = maxOf(maxLen, i - leftBound)
            } else if (netOpen < 0) {
                leftBound = i
                netOpen = 0
            }
        }

        var rightBound = s.length
        var netClose = 0
        for (i in s.indices.reversed()) {
            netClose += (s[i] - '(') * 2 - 1
            if (netClose == 0) {
                maxLen = maxOf(maxLen, rightBound - i)
            } else if (netClose < 0) {
                rightBound = i
                netClose = 0
            }
        }

        return maxLen
    }
}
