package com.hj.leetcode.kotlin.problem633

import kotlin.math.sqrt

/**
 * LeetCode page: [633. Sum of Square Numbers](https://leetcode.com/problems/sum-of-square-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(sqrt(c)) and Space O(1);
     */
    fun judgeSquareSum(c: Int): Boolean {
        var a = 0L
        var b = sqrt(c.toDouble()).toLong()
        while (a <= b) {
            val squareSum = a * a + b * b
            when {
                squareSum < c -> a++
                squareSum > c -> b--
                else -> return true
            }
        }
        return false
    }
}