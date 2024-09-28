package com.hj.leetcode.kotlin.problem1894

/**
 * LeetCode page: [1894. Find the Student that Will Replace the Chalk](https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of chalk;
     */
    fun chalkReplacer(
        chalk: IntArray,
        k: Int,
    ): Int {
        val chalkSum = chalk.fold(0L) { acc, e -> acc + e }
        var remainingChalks = k % chalkSum
        for ((i, chk) in chalk.withIndex()) {
            if (remainingChalks < chk) {
                return i
            }
            remainingChalks -= chk
        }
        throw IllegalStateException()
    }
}
