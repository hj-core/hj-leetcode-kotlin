package com.hj.leetcode.kotlin.problem279

/**
 * LeetCode page: [279. Perfect Squares](https://leetcode.com/problems/perfect-squares/);
 */
class Solution {
    /* Complexity:
     * Time O(n^(3/2)) and Space O(n);
     */
    fun numSquares(n: Int): Int {
        val leastPerNum = IntArray(n + 1) { num -> num }

        for (num in 1..n) {
            for (i in 1..num.floorSqrt()) {
                leastPerNum[num] = minOf(leastPerNum[num], 1 + leastPerNum[num - i * i])
            }
        }

        return leastPerNum[n]
    }

    private fun Int.floorSqrt() = Math.sqrt(this.toDouble()).toInt()
}