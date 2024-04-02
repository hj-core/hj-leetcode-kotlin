package com.hj.leetcode.kotlin.problem205

/**
 * LeetCode page: [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(min(N, C)) where N is the length of s
     * and C is the cardinality of charset;
     */
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        // The last found index of a char
        val sLastFound = hashMapOf<Char, Int>()
        val tLastFound = hashMapOf<Char, Int>()

        for (i in s.indices) {
            if (sLastFound[s[i]] != tLastFound[t[i]]) {
                return false
            }
            sLastFound[s[i]] = i
            tLastFound[t[i]] = i
        }
        return true
    }
}