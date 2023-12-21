package com.hj.leetcode.kotlin.problem279

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

    private fun Int.floorSqrt() = Math.sqrt(this.toDouble()).toInt()

    private fun Int.isSumOfTwoPerfectSquares(): Boolean {
        var left = 0
        var right = floorSqrt()

        while (left <= right) {
            val squareSum = left * left + right * right
            when {
                squareSum < this -> left++
                squareSum > this -> right--
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