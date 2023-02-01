package com.hj.leetcode.kotlin.problem1071

/**
 * LeetCode page: [1071. Greatest Common Divisor of Strings](https://leetcode.com/problems/greatest-common-divisor-of-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(|str1|+|str2|) & Space O(min(|str1|, |str2|));
     */
    fun gcdOfStrings(str1: String, str2: String): String {
        val isGcdExist = isGcdExist(str1, str2)
        if (!isGcdExist) return ""

        val gcdLength = findGcdLength(str1, str2)
        return str1.slice(0 until gcdLength)
    }

    private fun isGcdExist(str1: String, str2: String): Boolean {
        val (long, short) = if (str1.length >= str2.length) str1 to str2 else str2 to str1
        val lengthDiff = long.length - short.length

        // Check str1 + str2 == str2 + str1
        return short.indices.all { short[it] == long[it] } &&
                short.indices.all { short[it] == long[lengthDiff + it] } &&
                (0 until lengthDiff).all { long[it] == long[short.length + it] }
    }

    private fun findGcdLength(str1: String, str2: String): Int {
        return gcd(str1.length, str2.length)
    }

    private fun gcd(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0)
        val (large, small) = if (a >= b) a to b else b to a
        return if (small == 0) large else gcd(small, large % small)
    }
}