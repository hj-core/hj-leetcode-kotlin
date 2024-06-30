package com.hj.leetcode.kotlin.problem1614

import kotlin.math.max

/**
 * LeetCode page: [1614. Maximum Nesting Depth of the Parentheses](https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun maxDepth(s: String): Int {
        var result = 0
        var unclosed = 0
        for (c in s) {
            when (c) {
                ')' -> unclosed -= 1
                '(' -> {
                    unclosed += 1
                    result = max(result, unclosed)
                }
            }
        }
        return result
    }
}