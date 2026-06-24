package com.hj.leetcode.kotlin.problem3699

/**
 * LeetCode page: [3699. Number of ZigZag Arrays I](https://leetcode.com/problems/number-of-zigzag-arrays-i/);
 */
class Solution2 {
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

        // Improve solution 1 with symmetry
        val dp = LongArray(maxV + 2)

        // Base case that len = 2
        for (v in maxV downTo 1) {
            dp[v] = dp[v + 1] + v - 1
        }

        for (len in 3..n) {
            if (len and 1 == 0) {
                for (v in maxV downTo 1) {
                    dp[v] = (dp[v - 1] + dp[v + 1]) % modulus
                }
            } else {
                for (v in 1..maxV) {
                    dp[v] = (dp[v + 1] + dp[v - 1]) % modulus
                }
            }
        }

        val halfTotal = if (n and 1 == 0) dp[1] else dp[maxV]
        return (halfTotal * 2).mod(modulus)
    }
}
