package com.hj.leetcode.kotlin.problem263

/**
 * LeetCode page: [263. Ugly Number](https://leetcode.com/problems/ugly-number/);
 */
class Solution {
    /* Complexity:
     * Time O(Log n) and Space O(1);
     */
    fun isUgly(n: Int): Boolean {
        val isNonPositive = n <= 0
        if (isNonPositive) return false

        val num = n
            .reduceAll(3)
            .reduceAll(5)

        return num.isPowerOfTwo()
    }

    private fun Int.reduceAll(factor: Int): Int {
        require(factor > 1)
        var num = this

        while (num % factor == 0) {
            num /= factor
        }
        return num
    }

    private fun Int.isPowerOfTwo() = this >= 1 && (this and (this - 1)) == 0
}