package com.hj.leetcode.kotlin.problem50

/**
 * LeetCode page: [50. Pow(x, n)](https://leetcode.com/problems/powx-n/);
 */
class Solution {
    /* Complexity:
     * Time O(Log n) and Space O(Log n);
     */
    fun myPow(x: Double, n: Int): Double {
        if (n == 0) {
            return 1.0
        }

        val factor = when {
            n.isEven() -> 1.0
            n < 0 -> 1.0 / x
            else -> x
        }
        return factor * myPow(x * x, n / 2)
    }

    private fun Int.isEven(): Boolean = this and 1 == 0
}