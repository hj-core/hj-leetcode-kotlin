package com.hj.leetcode.kotlin.problem1935

/**
 * LeetCode page: [1935. Maximum Number of Words You Can Type](https://leetcode.com/problems/maximum-number-of-words-you-can-type/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(1) where N and M are the length
    // of text and brokenLetters, respectively.
    fun canBeTypedWords(
        text: String,
        brokenLetters: String,
    ): Int {
        var result = 0
        val maskValid = calcMask(brokenLetters).inv()
        var isValid = 1

        for (c in text) {
            if (c == ' ') {
                result += isValid
                isValid = 1
            } else {
                isValid = isValid and (maskValid shr (c - 'a'))
            }
        }
        result += isValid

        return result
    }

    private fun calcMask(letters: String): Int =
        letters.fold(0) { acc, c ->
            acc or (1 shl (c - 'a'))
        }
}
