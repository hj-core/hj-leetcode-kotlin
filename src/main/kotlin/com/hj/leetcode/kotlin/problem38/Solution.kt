package com.hj.leetcode.kotlin.problem38

/**
 * LeetCode page: [38. Count and Say](https://leetcode.com/problems/count-and-say/);
 */
class Solution {
    /* Complexity:
     * Time O(2^N) and Space O(2^N) where N equals n;
     */
    fun countAndSay(n: Int): String {
        var digits = "1"

        repeat(n - 1) { digits = countAndSay(digits) }
        return digits
    }

    private fun countAndSay(digits: String): String {
        val builder = StringBuilder()
        var currDigit = digits[0]
        var digitCount = 0

        fun updateBuilder() = builder
            .append(digitCount)
            .append(currDigit)

        for (digit in digits) {
            if (digit == currDigit) {
                digitCount++
            } else {
                updateBuilder()
                currDigit = digit
                digitCount = 1
            }
        }

        updateBuilder()
        return builder.toString()
    }
}