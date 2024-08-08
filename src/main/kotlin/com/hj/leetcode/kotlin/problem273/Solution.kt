package com.hj.leetcode.kotlin.problem273

/**
 * LeetCode page: [273. Integer to English Words](https://leetcode.com/problems/integer-to-english-words/);
 */
class Solution {

    private val primitives = mapOf(
        0 to "Zero",
        1 to "One",
        2 to "Two",
        3 to "Three",
        4 to "Four",
        5 to "Five",
        6 to "Six",
        7 to "Seven",
        8 to "Eight",
        9 to "Nine",
        10 to "Ten",
        11 to "Eleven",
        12 to "Twelve",
        13 to "Thirteen",
        14 to "Fourteen",
        15 to "Fifteen",
        16 to "Sixteen",
        17 to "Seventeen",
        18 to "Eighteen",
        19 to "Nineteen",
        20 to "Twenty",
        30 to "Thirty",
        40 to "Forty",
        50 to "Fifty",
        60 to "Sixty",
        70 to "Seventy",
        80 to "Eighty",
        90 to "Ninety",
        100 to "Hundred",
        1_000 to "Thousand",
        1_000_000 to "Million",
        1_000_000_000 to "Billion",
    )

    /* Complexity:
     * Time O(Log(num)) and Space O(Log(num));
     */
    fun numberToWords(num: Int): String {
        if (num == 0) {
            return checkNotNull(primitives[0])
        }

        val words = ArrayDeque<String>()
        var iNum = num
        var unit = 1

        while (iNum > 0) {
            val belowThousand = iNum % 1000
            if (belowThousand != 0) {
                if (unit != 1) {
                    words.addFirst(checkNotNull(primitives[unit]))
                }
                words.addFirst(translate(belowThousand))
            }
            iNum /= 1000
            unit *= 1000
        }
        return words.joinToString(separator = " ")
    }

    private fun translate(belowThousand: Int): String {
        require(belowThousand in 0..<1000)
        if (belowThousand == 0) {
            return checkNotNull(primitives[0])
        }

        val words = mutableListOf<String>()
        if (belowThousand >= 100) {
            words.add("${primitives[belowThousand / 100]} ${primitives[100]}")
        }

        val belowHundred = belowThousand % 100
        when {
            belowHundred == 0 -> {}
            belowHundred < 10 -> words.add(checkNotNull(primitives[belowHundred]))
            belowHundred in 11..<20 -> words.add(checkNotNull(primitives[belowHundred]))
            belowHundred % 10 == 0 -> words.add(checkNotNull(primitives[belowHundred]))
            else -> {
                val belowTen = belowHundred % 10
                words.add(checkNotNull(primitives[belowHundred - belowTen]))
                words.add(checkNotNull(primitives[belowTen]))
            }
        }
        return words.joinToString(separator = " ")
    }
}