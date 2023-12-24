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
        var pattern10 = 0
        var pattern01 = 0
        for ((index, c) in s.withIndex()) {
            val digit = c.digitToInt()
            val indicator = digit xor (index and 1)
            pattern10 += indicator
            pattern01 += 1 - indicator
        }
        return min(pattern10, pattern01)
    }
}