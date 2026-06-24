package com.hj.leetcode.kotlin.problem3699

/**
 * LeetCode page: [3699. Number of ZigZag Arrays I](https://leetcode.com/problems/number-of-zigzag-arrays-i/);
 */
class Solution {
    // Complexity:
    // Time O(n * (r - l)) and Space O(r - l).
    fun zigZagArrays(
        n: Int,
        l: Int,
        r: Int,
    ): Int {
        val modulus = 1_000_000_007
        // Normalize the range [l, r] to [1, r-l+1]
        val maxV = r - l + 1

        // dp[len&1][v][DIR] := the number of zigzag arrays of length 'len', modulo 'modulus',
        // starting with a value <= v and moving up (if DIR = 0) or down (if DIR = 1).
        //
        // We only keep the most recent two sub results.
        val dp = Array(2) { Array(maxV + 2) { LongArray(2) } }

        // Base case that len = 2
        for (v in 1..maxV) {
            dp[0][v][0] = dp[0][v - 1][0] + v - 1
            dp[0][v][1] = dp[0][v - 1][1] + maxV - v
        }

        for (i in 3..n) {
            val prevDp = dp[i and 1 xor 1]
            val currDp = dp[i and 1]
            for (v in 1..maxV) {
                currDp[v][0] = (currDp[v - 1][0] + prevDp[v - 1][1]) % modulus
                currDp[v][1] =
                    (currDp[v - 1][1] + prevDp[maxV][0] - prevDp[v][0] + modulus) % modulus
            }
        }

        return dp[n and 1][maxV].sum().mod(modulus)
    }
}
