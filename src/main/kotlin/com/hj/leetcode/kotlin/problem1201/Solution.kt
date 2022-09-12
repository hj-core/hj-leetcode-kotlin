package com.hj.leetcode.kotlin.problem1201

/**
 * LeetCode page: [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/);
 */
class Solution {
    private val divisors = longArrayOf(0L, 0L, 0L)
    private val lcmBetweenDivisors = longArrayOf(0L, 0L, 0L)
    private var lcm = 0L

    /* Complexity:
     * Time O(Log(NMK)) and Space O(1) where N, M, K equal a, b and c;
     */
    fun nthUglyNumber(n: Int, a: Int, b: Int, c: Int): Int {
        updateDivisorsAndLcms(a, b, c)
        val nthUgly = getNthUglyNumber(n)
        clearStates()
        return nthUgly
    }

    private fun updateDivisorsAndLcms(num1: Int, num2: Int, num3: Int) {
        divisors[0] = num1.toLong()
        divisors[1] = num2.toLong()
        divisors[2] = num3.toLong()
        lcmBetweenDivisors[0] = divisors[0].leastCommonMultiple(divisors[1])
        lcmBetweenDivisors[1] = divisors[1].leastCommonMultiple(divisors[2])
        lcmBetweenDivisors[2] = divisors[2].leastCommonMultiple(divisors[0])
        lcm = lcmBetweenDivisors[0].leastCommonMultiple(divisors[2])
    }

    private fun Long.leastCommonMultiple(other: Long): Long {
        require(this > 0L && other > 0L) { "Require positive inputs." }
        val gcd = this.greatestCommonDivisor(other)
        return this * other / gcd
    }

    private fun Long.greatestCommonDivisor(other: Long): Long {
        require(this > 0L && other > 0L) { "Require positive inputs." }
        val (smaller, larger) = if (this < other) this to other else other to this
        return if (larger % smaller == 0L) smaller else processEuclideanGcdAlgorithm(smaller, larger)
    }

    private tailrec fun processEuclideanGcdAlgorithm(smaller: Long, larger: Long): Long =
        if (smaller == 0L) larger else processEuclideanGcdAlgorithm(larger % smaller, smaller)

    private fun getNthUglyNumber(n: Int): Int {
        val numberOfUglyWithinLcm = getNumberOfUglyWithin(lcm)
        var nThUgly = (n / numberOfUglyWithinLcm) * lcm
        val rem = n % numberOfUglyWithinLcm
        if (rem != 0L) {
            val remThUgly = getNthUglyNumberWithinLcm(rem.toInt())
            nThUgly += remThUgly
        }
        return nThUgly.toInt()
    }

    private fun getNumberOfUglyWithin(value: Long): Long {
        val naiveCount = divisors.let { value / it[0] + value / it[1] + value / it[2] }
        val duplication = -value / lcm +
                value / lcmBetweenDivisors[0] +
                value / lcmBetweenDivisors[1] +
                value / lcmBetweenDivisors[2]
        return naiveCount - duplication
    }

    private fun getNthUglyNumberWithinLcm(n: Int): Long {
        var leftBound = 1L
        var rightBound = lcm
        while (leftBound <= rightBound) {
            val midValue = (leftBound + rightBound) shr 1
            val numberOfUglyAtMid = getNumberOfUglyWithin(midValue)
            if (numberOfUglyAtMid >= n) rightBound = midValue - 1 else leftBound = midValue + 1
        }
        return leftBound
    }

    private fun clearStates() {
        divisors.fill(0L)
        lcmBetweenDivisors.fill(0L)
        lcm = 0L
    }
}