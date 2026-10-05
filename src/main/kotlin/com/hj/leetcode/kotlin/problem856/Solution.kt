package com.hj.leetcode.kotlin.problem856

/**
 * LeetCode page: [856. Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun scoreOfParentheses(s: String): Int {
        var score = 0
        var depth = 1
        for (i in 1..<s.length) {
            depth += 1 - (s[i].code and 1 shl 1)
            score += (s[i - 1].code xor s[i].code and s[i].code) shl depth
        }
        return score
    }
}
