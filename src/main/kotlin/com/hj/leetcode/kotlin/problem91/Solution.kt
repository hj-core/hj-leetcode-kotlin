package com.hj.leetcode.kotlin.problem91

/**
 * LeetCode page: [91. Decode Ways](https://leetcode.com/problems/decode-ways/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun numDecodings(s: String): Int {
        val cannotDecodeLastDigit = cannotDecode(s, s.lastIndex)
        if (cannotDecodeLastDigit) return 0

        val lastDigit = getDigit(s, s.lastIndex)
        val numWaysOfLastDigit = if (lastDigit == 0) 0 else 1

        return findNumDecodeWaysBackward(
            currIndex = s.lastIndex - 1,
            digits = s,
            prevNumWays = numWaysOfLastDigit,
            prevPrevNumWays = 1 // Determined by the logic of algorithm;
        )
    }

    private fun cannotDecode(digits: String, index: Int): Boolean {
        val isNonZero = digits[index] != '0'
        if (isNonZero) return false

        val isLeadingZero = index == 0
        if (isLeadingZero) return true

        val precedingDigit = getDigit(digits, index - 1)
        return precedingDigit != 1 && precedingDigit != 2
    }

    private fun getDigit(digits: String, index: Int): Int {
        require(digits[index].isDigit())
        return digits[index] - '0'
    }

    private tailrec fun findNumDecodeWaysBackward(
        currIndex: Int,
        digits: String,
        prevNumWays: Int,
        prevPrevNumWays: Int
    ): Int {
        if (currIndex == -1) return prevNumWays
        if (cannotDecode(digits, currIndex)) return 0

        val keyDigits = getSubDigits(digits, currIndex..currIndex + 1)
        val currNumWays = when {
            keyDigits < 10 -> 0
            keyDigits > 26 -> prevNumWays
            else -> prevNumWays + prevPrevNumWays
        }

        return findNumDecodeWaysBackward(currIndex - 1, digits, currNumWays, prevNumWays)
    }

    private fun getSubDigits(digits: String, intRange: IntRange): Int {
        return digits.slice(intRange).toInt()
    }
}