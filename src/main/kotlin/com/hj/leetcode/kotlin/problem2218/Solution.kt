package com.hj.leetcode.kotlin.problem2218

/**
 * LeetCode page: [2218. Maximum Value of K Coins From Piles](https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/);
 */
class Solution {
    // Complexity:
    // Time O(kL) and Space O(k) where L is the flattened
    // length of piles.
    fun maxValueOfCoins(
        piles: List<List<Int>>,
        k: Int,
    ): Int {
        // dp[j]@i:= the maximum total by taking j coins from
        // piles[..=i].
        val dp = IntArray(k + 1)

        // prevDp[j]:= dp[j]@i-1
        val prevDp = IntArray(k + 1)

        for (pile in piles) {
            // Update dp to include the current pile based on
            // how many coins we take from this pile.
            dp.copyInto(prevDp)
            var prefixSum = 0
            for (took in 1..minOf(pile.size, k)) {
                prefixSum += pile[took - 1]
                for (j in took..k) {
                    dp[j] = maxOf(dp[j], prefixSum + prevDp[j - took])
                }
            }
        }
        return dp[k]
    }
}
