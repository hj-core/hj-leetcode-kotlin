package com.hj.leetcode.kotlin.problem1071

/**
 * LeetCode page: [1071. Greatest Common Divisor of Strings](https://leetcode.com/problems/greatest-common-divisor-of-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(|str1|+|str2|) & Space O(min(|str1|, |str2|));
     */
    fun gcdOfStrings(str1: String, str2: String): String {
        val hasNoGcd = hasNoGcd(str1, str2)
        if (hasNoGcd) return ""

        val potentialGcd = findPotentialGcd(str1, str2)
        val isValidGcd = verifyGcd(str1, str2, potentialGcd)
        return if (isValidGcd) potentialGcd else ""
    }

    private fun hasNoGcd(str1: String, str2: String): Boolean {
        val (long, short) = if (str1.length >= str2.length) str1 to str2 else str2 to str1
        val lengthDiff = long.length - short.length

        // Check str1 + str2 == str2 + str1
        return short.indices.any { short[it] != long[it] } &&
                short.indices.any { short[it] != long[lengthDiff + it] } &&
                (0 until lengthDiff).any { long[it] != long[short.length + it] }
    }

    private fun findPotentialGcd(str1: String, str2: String): String {
        val length = gcd(str1.length, str2.length)
        return str1.slice(0 until length)
    }

    private fun gcd(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0)
        val (large, small) = if (a >= b) a to b else b to a
        return if (small == 0) large else gcd(small, large % small)
    }

    private fun verifyGcd(str1: String, str2: String, potentialGcd: String): Boolean {
        return str1.isRepeating(potentialGcd) && str2.isRepeating(potentialGcd)
    }

    private fun String.isRepeating(baseStr: String): Boolean {
        if (this.length % baseStr.length != 0) return false
        if (this.length == baseStr.length) return this == baseStr

        for (i in baseStr.length until this.length step baseStr.length) {
            for (j in baseStr.indices) {
                if (this[i + j] != this[i + j - baseStr.length]) {
                    return false
                }
            }
        }
        return true
    }
}