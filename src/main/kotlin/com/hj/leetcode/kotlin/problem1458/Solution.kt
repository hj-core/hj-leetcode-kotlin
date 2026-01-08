package com.hj.leetcode.kotlin.problem1458

/**
 * LeetCode page: [1458. Max Dot Product of Two Subsequences](https://leetcode.com/problems/max-dot-product-of-two-subsequences/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N) where M and N are the lengths of nums1
    // and nums2, respectively.
    fun maxDotProduct(
        nums1: IntArray,
        nums2: IntArray,
    ): Int {
        val m = nums1.size
        val n = nums2.size

        // dp[j]@i:= maxDotProduct(nums1[i..], nums2[j..])
        val dp = IntArray(n)

        // Base case where i equals m-1
        dp[n - 1] = nums1[m - 1] * nums2[n - 1]
        for (j in n - 2 downTo 0) {
            dp[j] = maxOf(dp[j + 1], nums1[m - 1] * nums2[j])
        }

        for (i in m - 2 downTo 0) {
            var oldNext: Int // dp[i+1][j+1]
            dp[n - 1] =
                maxOf(dp[n - 1], nums1[i] * nums2[n - 1]).also {
                    oldNext = dp[n - 1]
                }
            for (j in n - 2 downTo 0) {
                dp[j] =
                    maxOf(
                        dp[j], // dp[i+1][j]
                        dp[j + 1], // dp[i][j+1]
                        nums1[i] * nums2[j] + maxOf(0, oldNext),
                    ).also { oldNext = dp[j] }
            }
        }

        return dp[0]
    }
}
