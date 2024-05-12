package com.hj.leetcode.kotlin.problem1758

import kotlin.math.min

/**
 * LeetCode page: [1758. Minimum Changes To Make Alternating Binary String](https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun minOperations(s: String): Int {
        var pattern01 = 0
        for ((index, c) in s.withIndex()) {
            val digit = c.digitToInt()
            val indicator = digit xor (index and 1)
            pattern01 += indicator
            // pattern10 += 1 - indicator
        }
        val pattern10 = s.length - pattern01
        return min(pattern01, pattern10)
    }
}