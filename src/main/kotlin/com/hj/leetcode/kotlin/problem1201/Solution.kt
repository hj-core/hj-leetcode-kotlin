package com.hj.leetcode.kotlin.problem1201

/**
 * LeetCode page: [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(Log(NMK)) and Space O(1) where N, M, K equal a, b and c;
     */
    fun nthUglyNumber(n: Int, a: Int, b: Int, c: Int): Int {
        val divisors = Divisors(a.toLong(), b.toLong(), c.toLong())
        return getNthUglyNumber(n, divisors)
    }

    private data class Divisors(val d1: Long, val d2: Long, val d3: Long) {
        val lcmD1D2 = leastCommonMultiple(d1, d2)
        val lcmD2D3 = leastCommonMultiple(d2, d3)
        val lcmD3D1 = leastCommonMultiple(d3, d1)
        val lcmD1D2D3 = leastCommonMultiple(lcmD1D2, d3)

        private fun leastCommonMultiple(long1: Long, long2: Long): Long {
            require(long1 > 0L && long2 > 0L) { "Require positive arguments." }
            val gcd = greatestCommonDivisor(long1, long2)
            return long1 * long2 / gcd
        }

        private fun greatestCommonDivisor(long1: Long, long2: Long): Long {
            require(long1 > 0L && long2 > 0L) { "Require positive arguments." }
            val (smaller, larger) = if (long1 < long2) long1 to long2 else long2 to long1
            return if (larger % smaller == 0L) smaller else euclideanGcdAlgorithm(smaller, larger)
        }

        private tailrec fun euclideanGcdAlgorithm(smaller: Long, larger: Long): Long {
            require(smaller <= larger)
            return if (smaller == 0L) larger else euclideanGcdAlgorithm(larger % smaller, smaller)
        }
    }

    private fun getNthUglyNumber(n: Int, divisors: Divisors): Int {
        val numberOfUglyWithinLcm = getNumberOfUglyWithin(divisors.lcmD1D2D3, divisors)
        var nThUgly = (n / numberOfUglyWithinLcm) * divisors.lcmD1D2D3
        val rem = n % numberOfUglyWithinLcm
        if (rem != 0L) {
            val remThUgly = getNthUglyNumberWithinLcm(rem.toInt(), divisors)
            nThUgly += remThUgly
        }
        return nThUgly.toInt()
    }

    private fun getNumberOfUglyWithin(value: Long, divisors: Divisors): Long {
        val naiveCount =
            divisors.run { value / d1  + value / d2 + value / d3 }
        val duplication =
            divisors.run { -value / lcmD1D2D3 + value / lcmD1D2 + value / lcmD2D3 + value / lcmD3D1 }
        return naiveCount - duplication
    }

    private fun getNthUglyNumberWithinLcm(n: Int, divisors: Divisors): Long {
        var leftBound = 1L
        var rightBound = divisors.lcmD1D2D3
        while (leftBound <= rightBound) {
            val midValue = (leftBound + rightBound) shr 1
            val numberOfUglyAtMid = getNumberOfUglyWithin(midValue, divisors)
            if (numberOfUglyAtMid >= n) rightBound = midValue - 1 else leftBound = midValue + 1
        }
        return leftBound
    }
}