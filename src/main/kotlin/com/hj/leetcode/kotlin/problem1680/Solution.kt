package com.hj.leetcode.kotlin.problem1680

/**
 * LeetCode page: [1680. Concatenation of Consecutive Binary Numbers](https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/);
 *
 * TODO 1 : There is a time O((LogN)^2) and space O(1) solution . The idea is to represent the concatenation in a
 *    series of matrix operations and the time saving comes from the algorithm of matrix's power that it can take Log
 *    instead of linear order complexity.
 *    ([REFERENCE](https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/discuss/1037649/))
 *
 * TODO 2 : There is another time O((LogN)^2) solution but not yet understand what's happening...
 *    ([REFERENCE](https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/discuss/963886))
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