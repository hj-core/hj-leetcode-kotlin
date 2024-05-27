package com.hj.leetcode.kotlin.problem552

/**
 * LeetCode page: [552. Student Attendance Record II](https://leetcode.com/problems/student-attendance-record-ii/);
 */
class Solution {
    /* Complexity:
     * TIme O(n) and Space O(1);
     */
    fun checkRecord(n: Int): Int {
        val module = 1_000_000_007
        /* dp[j][k]@i::= the number of possible length i records that
         * have j absence and k leading late.
         */
        val dp = Array(2) { IntArray(3) }.apply {
            this[0][0] = 1 // base case that i = 0
        }

        repeat(n) {
            val prev = arrayOf(dp[0].clone(), dp[1].clone())
            dp[0][0] = prev[0].fold(0) { acc, i -> (acc + i) % module }
            dp[0][1] = prev[0][0]
            dp[0][2] = prev[0][1]
            dp[1][0] = prev.sumOf { it.reduce { acc, i -> (acc + i) % module } }% module
            dp[1][1] = prev[1][0]
            dp[1][2] = prev[1][1]
        }
        return dp.sumOf { it.reduce { acc, i -> (acc + i) % module } } % module
    }
}