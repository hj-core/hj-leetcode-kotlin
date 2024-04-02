package com.hj.leetcode.kotlin.problem205

/**
 * LeetCode page: [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        val mappingS = hashMapOf<Char, Char>() // s[i] to t[i]
        val mappingT = hashMapOf<Char, Char>() // t[i] to s[i]
        for (i in s.indices) {
            if (mappingS[s[i]]?.let { it != t[i]} == true) {
                return false
            }
            if (mappingT[t[i]]?.let { it != s[i] } == true) {
                return false
            }
            mappingS.computeIfAbsent(s[i]) { t[i] }
            mappingT.computeIfAbsent(t[i]) { s[i] }
        }
        return true
    }
}