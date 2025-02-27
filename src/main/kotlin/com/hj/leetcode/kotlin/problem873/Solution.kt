package com.hj.leetcode.kotlin.problem873

/**
 * LeetCode page: [873. Length of Longest Fibonacci Subsequence](https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N^2) where N is the length of `arr`.
    fun lenLongestFibSubseq(arr: IntArray): Int {
        // dp[k][j]::= the length of longest fib-like sequence ending with (arr[j], arr[k])
        val dp = Array(arr.size) { k -> IntArray(k) { 2 } }
        var result = 0

        for (k in 2..<arr.size) {
            var i = 0
            for (j in k - 1 downTo 0) {
                val diff = arr[k] - arr[j]
                while (i < j && arr[i] < diff) {
                    i++
                }
                if (i == j) {
                    break
                }
                if (arr[i] == diff) {
                    dp[k][j] = 1 + dp[j][i]
                    result = maxOf(result, dp[k][j])
                }
            }
        }
        return if (result < 3) 0 else result
    }
}
