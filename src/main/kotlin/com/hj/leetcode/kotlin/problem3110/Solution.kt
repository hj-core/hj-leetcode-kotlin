package com.hj.leetcode.kotlin.problem3110

import kotlin.math.abs

/**
 * LeetCode page: [3110. Score of a String](https://leetcode.com/problems/score-of-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun scoreOfString(s: String): Int {
        return s
            .asSequence()
            .windowed(2)
            .sumOf { (c1, c2) -> abs(c1 - c2) }
    }
}