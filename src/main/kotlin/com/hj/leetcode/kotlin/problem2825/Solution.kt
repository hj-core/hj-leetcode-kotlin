package com.hj.leetcode.kotlin.problem2825

/**
 * LeetCode page: [2825. Make String a Subsequence Using Cyclic Increments](https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of str1.
     */
    fun canMakeSubsequence(
        str1: String,
        str2: String,
    ): Boolean {
        var matched = 0
        for (c1 in str1) {
            if (canMake(c1, str2[matched])) {
                matched++
                if (matched == str2.length) {
                    return true
                }
            }
        }
        return false
    }

    private fun canMake(
        from: Char,
        to: Char,
    ): Boolean =
        when (from) {
            to -> true
            'z' -> to == 'a'
            else -> from + 1 == to
        }
}
