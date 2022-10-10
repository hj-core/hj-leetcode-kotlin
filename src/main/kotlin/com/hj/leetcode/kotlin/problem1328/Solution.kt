package com.hj.leetcode.kotlin.problem1328

/**
 * LeetCode page: [1328. Break a Palindrome](https://leetcode.com/problems/break-a-palindrome/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of palindrome;
     */
    fun breakPalindrome(palindrome: String): String {
        if (palindrome.length == 1) return ""

        val ans = StringBuilder(palindrome)
        val halfLength = palindrome.length shr 1

        for (index in 0 until halfLength) {
            if (ans[index] != 'a') {
                ans[index] = 'a'
                return ans.toString()
            }
        }

        ans[ans.lastIndex] = 'b'
        return ans.toString()
    }
}