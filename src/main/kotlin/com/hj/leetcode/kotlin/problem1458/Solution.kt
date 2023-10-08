package com.hj.leetcode.kotlin.problem1458

/**
 * LeetCode page: [1458. Max Dot Product of Two Subsequences](https://leetcode.com/problems/max-dot-product-of-two-subsequences/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M is the size of nums1 and N is
     * the size of nums2;
     */
    fun maxDotProduct(nums1: IntArray, nums2: IntArray): Int {
        // dp[i][j] ::= maxDotProduct(nums1[i:], nums2[j:])
        val dp = Array(nums1.size + 1) {
            IntArray(nums2.size + 1) { Int.MIN_VALUE }
        }

        for (i in nums1.indices.reversed()) {
            for (j in nums2.indices.reversed()) {
                dp[i][j] = maxOf(
                    nums1[i] * nums2[j] + (dp[i + 1][j + 1].coerceAtLeast(0)),
                    dp[i + 1][j],
                    dp[i][j + 1]
                )
            }
        }
        return dp[0][0]
    }
}