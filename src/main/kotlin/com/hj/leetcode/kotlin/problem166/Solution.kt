package com.hj.leetcode.kotlin.problem166

import kotlin.math.abs

/**
 * LeetCode page: [166. Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal/);
 */
class Solution {
    // Complexity:
    // Time O(denominator) and Space O(denominator) according to
    // https://en.wikipedia.org/wiki/Repeating_decimal#Other_properties_of_repetend_lengths.
    fun fractionToDecimal(
        numerator: Int,
        denominator: Int,
    ): String {
        // Long division
        val quotients = mutableListOf<Long>()
        val remainders = hashMapOf<Long, Int>() // remainder to long division step
        remainders[0] = -1

        var step = 0
        var n = abs(numerator.toLong())
        val m = abs(denominator.toLong())

        while (true) {
            step++
            quotients.add(n / m)
            val r = n % m
            if (r in remainders) {
                n = r
                break
            }
            remainders[r] = step
            n = r * 10
        }

        // Build result string
        val sb = StringBuilder()
        if (isNegative(numerator, denominator)) {
            sb.append("-")
        }

        sb.append(quotients[0])
        if (quotients.size > 1) {
            sb.append(".")
        }

        remainders[0] = quotients.size
        val repeatStart = checkNotNull(remainders[n])
        for (i in 1..<repeatStart) {
            sb.append(quotients[i])
        }

        if (repeatStart < quotients.size) {
            sb.append("(")
            for (i in repeatStart..<quotients.size) {
                sb.append(quotients[i])
            }
            sb.append(")")
        }

        return sb.toString()
    }

    private fun isNegative(
        numerator: Int,
        denominator: Int,
    ): Boolean = (numerator < 0 && denominator > 0) || (numerator > 0) && (denominator < 0)
}
