package com.hj.leetcode.kotlin.problem3272

/**
 * LeetCode page: [3272. Find the Count of Good Integers](https://leetcode.com/problems/find-the-count-of-good-integers/);
 */
class Solution {
    // Complexity:
    // Time O(10^(n/2)) and Space O(10^(n/2)).
    fun countGoodIntegers(
        n: Int,
        k: Int,
    ): Long {
        val goodFreqs = mutableSetOf<List<Int>>()
        collectGoodFrequencies(0, n, k, IntArray(n), goodFreqs)

        val facTable = computeFactorialTable(n)
        var result = 0L
        for (freq in goodFreqs) {
            result += countIntegers(n, freq, facTable)
        }
        return result
    }

    // collectGoodFrequencies collects the distinct digit frequencies of n-digit
    // palindromic numbers that are divisible by k.
    private fun collectGoodFrequencies(
        i: Int, // the index of current digit
        n: Int,
        k: Int,
        digits: IntArray, // the digits of the palindrome
        collector: MutableSet<List<Int>>, // a set stores the digit frequencies of good integers.
    ) {
        if (i == (n + 1) / 2) {
            val number = computeNumber(digits)
            if (number % k == 0L) {
                val freq = computeFreq(digits)
                collector.add(freq)
            }

            return
        }

        val minDigit = if (i == 0) 1 else 0
        for (digit in minDigit..<10) {
            digits[i] = digit
            digits[n - i - 1] = digit
            collectGoodFrequencies(i + 1, n, k, digits, collector)
        }
    }

    // computeNumber returns the number formed by digits(MSD to LSD).
    private fun computeNumber(digits: IntArray): Long = digits.fold(0L) { acc, digit -> acc * 10 + digit }

    // computeFreq returns the frequency of each digit.
    private fun computeFreq(digits: IntArray): List<Int> {
        val result = MutableList(10) { 0 }
        for (digit in digits) {
            result[digit]++
        }
        return result
    }

    // computeFactorialTable returns the factorial of numbers up to n.
    private fun computeFactorialTable(n: Int): LongArray {
        val result = LongArray(n + 1)
        result[0] = 1
        for (num in 1..n) {
            result[num] = result[num - 1] * num
        }
        return result
    }

    // countIntegers returns the number of distinct integers that have the digitFreq.
    // Leading zero is prohibited.
    private fun countIntegers(
        n: Int,
        digitFreq: List<Int>,
        factorialTable: LongArray,
    ): Long {
        val nonZeroDigits = digitFreq.sum() - digitFreq[0]
        var result = nonZeroDigits.toLong()
        result *= factorialTable[n - 1]

        for (freq in digitFreq) {
            result /= factorialTable[freq]
        }
        return result
    }
}
