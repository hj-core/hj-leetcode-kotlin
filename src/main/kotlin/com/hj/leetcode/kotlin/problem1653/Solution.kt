package com.hj.leetcode.kotlin.problem1653

import kotlin.math.min

/**
 * LeetCode page: [1653. Minimum Deletions to Make String Balanced](https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun minimumDeletions(s: String): Int {
        var aAfter = s.count { it == 'a' }
        var bBefore = 0

        var result = s.length - aAfter // Case that we delete all 'b's
        for (firstB in s.indices) {
            if (s[firstB] == 'a') {
                aAfter--
            } else {
                result = min(result, bBefore + aAfter)
                bBefore++
            }
        }
        return result
    }
}