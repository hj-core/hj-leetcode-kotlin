package com.hj.leetcode.kotlin.problem205

/**
 * LeetCode page: [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(min(N,C)) where N is the length of s
     * and C is the cardinality of the possible charset;
     */
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        val mappingS = hashMapOf<Char, Char>() // s[i] to t[i]
        val mappingT = hashMapOf<Char, Char>() // t[i] to s[i]
        for (i in s.indices) {
            if (s[i] in mappingS && mappingS[s[i]] != t[i]) {
                return false
            }
            if (t[i] in mappingT && mappingT[t[i]] != s[i]) {
                return false
            }
            mappingS[s[i]] = t[i]
            mappingT[t[i]] = s[i]
        }
        return true
    }
}