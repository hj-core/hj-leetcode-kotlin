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
    ): Boolean {
        if (s.length < k) {
            return false
        }
        if (s.length == k || k >= 26) {
            return true
        }

        // isFreqOdd[c-'a']::= is the frequency of c an odd number
        val isFreqOdd = BooleanArray(26)
        for (c in s) {
            isFreqOdd[c - 'a'] = !isFreqOdd[c - 'a']
        }
        return isFreqOdd.count { it } <= k
    }
}
