package com.hj.leetcode.kotlin.problem343

/**
 * LeetCode page: [343. Integer Break](https://leetcode.com/problems/integer-break/);
 */
class Solution {
    /* Complexity:
     * Time O(n^2) and Space O(n);
     */
    fun integerBreak(n: Int): Int {
        val dp = IntArray(n + 1) // dp[i] ::= integerBreak(i)
        dp[1] = 1

        for (i in 2..n) {
            var maxProduct = 1
            for (j in 1 until i) {
                maxProduct = maxOf(maxProduct, j * dp[i - j], j * (i - j))
            }
            dp[i] = maxProduct
        }
        return dp[n]
    }
}