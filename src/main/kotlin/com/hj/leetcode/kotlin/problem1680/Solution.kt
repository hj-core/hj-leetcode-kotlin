package com.hj.leetcode.kotlin.problem1680

/**
 * LeetCode page: [1680. Concatenation of Consecutive Binary Numbers](https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N equals n;
     */
    fun concatenatedBinary(n: Int): Int {
        var accSum = 0L
        var bitLengthOfNum = 0
        val mod = 1_000_000_007

        for (num in 1..n) {
            if (num.isPowerOfTwo()) bitLengthOfNum++
            accSum = ((accSum shl bitLengthOfNum) + num) % mod
        }
        return accSum.toInt()
    }

    private fun Int.isPowerOfTwo() = this and (this - 1) == 0
}