package com.hj.leetcode.kotlin.problem1625

/**
 * LeetCode page: [1625. Lexicographically Smallest String After Applying Operations](https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/);
 */
class Solution {
    // Complexity:
    // Time is O(N^2) and Space is O(N) where N is the length of s.
    // Here, we treat the number of different digits (which is 10)
    // as a constant.
    fun findLexSmallestString(
        s: String,
        a: Int,
        b: Int,
    ): String {
        val n = s.length
        val result = s.toCharArray()
        val s2 = s + s
        val shift = gcd(n, b)

        for (start in 0..<n step shift) {
            // subtrahends applied to the values at even and odd
            // indices, respectively.
            val subtrahends = intArrayOf(0, calcSubtrahend(s2[start + 1], a))
            if (b and 1 == 1) {
                subtrahends[0] = calcSubtrahend(s2[start], a)
            }

            var isSmaller = false
            var i = 0
            while (i < n) {
                val newChar = calcNewChar(s2[start + i], subtrahends[i and 1])
                if (newChar < result[i]) {
                    isSmaller = true
                    result[i] = newChar
                    i++
                    break
                } else if (newChar > result[i]) {
                    break
                }

                i++
            }

            if (isSmaller) {
                while (i < n) {
                    result[i] = calcNewChar(s2[start + i], subtrahends[i and 1])
                    i++
                }
            }
        }

        return String(result)
    }

    private tailrec fun gcd(
        a: Int,
        b: Int,
    ): Int = if (b == 0) a else gcd(b, a % b)

    // Compute the difference between the original char and the
    // minimum char achievable by adding a.
    private fun calcSubtrahend(
        char: Char,
        a: Int,
    ): Int {
        val digit = char - '0'
        val minDigit = (0..<10).minOf { (digit + a * it) % 10 }
        return digit - minDigit
    }

    private fun calcNewChar(
        char: Char,
        subtrahend: Int,
    ): Char {
        var result = char - subtrahend
        if (result < '0') {
            result += 10
        }
        return result
    }
}
