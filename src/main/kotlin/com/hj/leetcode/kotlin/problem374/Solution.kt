package com.hj.leetcode.kotlin.problem374

/**
 * LeetCode page: [374. Guess Number Higher or Lower](https://leetcode.com/problems/guess-number-higher-or-lower/);
 */
class Solution : GuessGame() {
    /* Complexity:
     * Time O(Log n) and Space O(1);
     */
    override fun guessNumber(n: Int): Int {
        var left = 1
        var right = n

        while (left <= right) {
            val mid = (left + right) ushr 1

            when (guess(mid)) {
                1 -> left = mid + 1
                -1 -> right = mid - 1
                0 -> return mid
            }
        }

        throw IllegalArgumentException("Number is not in range 1 to n")
    }
}

abstract class GuessGame {
    abstract fun guessNumber(n: Int): Int
    fun guess(n: Int): Int = -1
}