package com.hj.leetcode.kotlin.problem2485

import kotlin.math.sqrt

/**
 * LeetCode page: [2485. Find the Pivot Integer](https://leetcode.com/problems/find-the-pivot-integer/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun pivotInteger(n: Int): Int {
        val sum = n * (n + 1) / 2
        val xUnsure = sqrt(sum.toDouble()).toInt()
        return if (xUnsure * xUnsure == sum) xUnsure else -1
    }
}