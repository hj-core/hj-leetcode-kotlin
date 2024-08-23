package com.hj.leetcode.kotlin.problem592

import java.util.regex.Pattern
import kotlin.math.abs

/**
 * LeetCode page: [592. Fraction Addition and Subtraction](https://leetcode.com/problems/fraction-addition-and-subtraction/);
 */
class Solution {
    /* Complexity:
     * Time O(?) and Space O(?) which depends on the complexity of regex matching;
     */
    fun fractionAddition(expression: String): String =
        getFractions(expression)
            .fold(Fraction.from("0/1")) { acc, s -> acc.plus(Fraction.from(s)) }
            .toString()

    private fun getFractions(expression: String): Sequence<String> =
        sequence {
            val regex = """([+-]?\d+/\d+)+?"""
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(expression)
            while (matcher.find()) {
                yield(matcher.group())
            }
        }

    private class Fraction private constructor(
        val numerator: Int,
        val denominator: Int,
    ) {
        fun plus(other: Fraction): Fraction {
            val newDenominator = lcm(denominator, other.denominator)
            val newNumerator =
                (
                    numerator * (newDenominator / denominator) +
                        other.numerator * (newDenominator / other.denominator)
                )
            val reduceFactor = gcd(abs(newNumerator), abs(newDenominator))
            return Fraction(newNumerator / reduceFactor, newDenominator / reduceFactor)
        }

        private fun lcm(
            a: Int,
            b: Int,
        ): Int = a / gcd(a, b) * b

        private tailrec fun gcd(
            a: Int,
            b: Int,
        ): Int {
            if (b == 0) return a
            return gcd(b, a % b)
        }

        override fun toString(): String = "$numerator/$denominator"

        companion object {
            fun from(str: String): Fraction {
                val (numerator, denominator) = str.split('/').map { it.toInt() }
                require(denominator > 0)
                return Fraction(numerator, denominator)
            }
        }
    }
}
