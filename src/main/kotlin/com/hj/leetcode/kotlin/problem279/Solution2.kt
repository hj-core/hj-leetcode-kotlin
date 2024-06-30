package com.hj.leetcode.kotlin.problem279

import kotlin.math.sqrt

/**
 * LeetCode page: [279. Perfect Squares](https://leetcode.com/problems/perfect-squares/);
 */
class Solution2 {
    /* Complexity:
     * Time O(sqrt(n)) and Space O(1);
     */
    fun numSquares(n: Int): Int {
        return when {
            n.isPerfectSquare() -> 1
            n.isSumOfTwoPerfectSquares() -> 2
            n.isSumOfThreePerfectSquares() -> 3
            else -> 4 // Lagrange's four-square theorem
        }
    }

    private fun Int.isPerfectSquare(): Boolean {
        val floorRoot = this.floorSqrt()
        return this == floorRoot * floorRoot
    }

    private fun Int.floorSqrt() = sqrt(this.toDouble()).toInt()

    private fun Int.isSumOfTwoPerfectSquares(): Boolean {
        var first = 0
        var second = floorSqrt()

        while (first <= second) {
            val squareSum = first * first + second * second
            when {
                squareSum < this -> first++
                squareSum > this -> second--
                else -> return true
            }
        }
        return false
    }

    private fun Int.isSumOfThreePerfectSquares(): Boolean {
        // Legendre's three-square theorem
        var num = this
        while (num and 3 == 0) {
            num = num shr 2
        }
        return (num and 7) != 7
    }
}