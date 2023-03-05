package com.hj.leetcode.kotlin.problem28

/**
 * LeetCode page: [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(M) where N and M are the length of haystack and needle;
     */
    fun strStr(haystack: String, needle: String): Int {
        if (haystack.length < needle.length) {
            return -1
        }
        return kmpSearch(haystack, needle)
    }

    private fun kmpSearch(text: String, pattern: String): Int {
        val pi = buildPrefixFunctionForKmp(pattern)
        var q = -1
        for ((index, char) in text.withIndex()) {
            while (q > -1 && pattern[q + 1] != char) {
                q = pi[q]
            }
            if (pattern[q + 1] == char) {
                q++
            }
            if (q == pattern.lastIndex) {
                // If it is to find all occurrences, set q to pi[q] here
                return index - pattern.length + 1
            }
        }
        return -1
    }

    private fun buildPrefixFunctionForKmp(pattern: String): IntArray {
        /* pi(q) ::= max { k : k < q and P_k is suffix of P_q } where P_k and P_q means
         * prefix of P up to index k and q inclusively.
         */
        val pi = IntArray(pattern.length).apply { this[0] = -1 }
        var k = pi[0]
        for (q in 1 until pattern.length) {
            while (k > -1 && pattern[k + 1] != pattern[q]) {
                k = pi[k]
            }
            if (pattern[k + 1] == pattern[q]) {
                k++
            }
            pi[q] = k
        }
        return pi
    }
}