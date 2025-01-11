package com.hj.leetcode.kotlin.problem1400

/**
 * LeetCode page: [1400. Construct K Palindrome Strings](https://leetcode.com/problems/construct-k-palindrome-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(M)
     * where N is the length of s and M is the number of possible chars.
     */
    fun canConstruct(
        s: String,
        k: Int,
    ): Boolean =
        when {
            s.length < k -> false
            s.length == k -> true
            else -> charFrequencies(s).count { it % 2 == 1 } <= k
        }

    private fun charFrequencies(s: String): IntArray {
        val result = IntArray(26)
        for (c in s) {
            result[c - 'a']++
        }
        return result
    }
}
