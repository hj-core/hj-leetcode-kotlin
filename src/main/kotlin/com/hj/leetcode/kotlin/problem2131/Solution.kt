package com.hj.leetcode.kotlin.problem2131

/**
 * LeetCode page: [2131. Longest Palindrome by Concatenating Two Letter Words](https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/);
 */
class Solution {
    // Complexity:
    // Time O(N+S^2) and Space O(S^2) where N is the length of words and
    // S is the size of character set, which is 26 for this problem.
    fun longestPalindrome(words: Array<String>): Int {
        val freq = Array(26) { IntArray(26) }
        for (word in words) {
            freq[word[0] - 'a'][word[1] - 'a']++
        }

        var result = 0
        var extraMid = false
        for (i in 0..<26) {
            for (j in 0..<i) {
                result += minOf(freq[i][j], freq[j][i]) * 4
            }

            result += (freq[i][i] / 2) * 4
            if (freq[i][i] and 1 == 1) {
                extraMid = true
            }
        }

        if (extraMid) {
            result += 2
        }
        return result
    }
}
