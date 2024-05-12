package com.hj.leetcode.kotlin.problem1561

/**
 * LeetCode page: [1561. Maximum Number of Coins You Can Get](https://leetcode.com/problems/maximum-number-of-coins-you-can-get/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of piles;
     */
    fun maxCoins(piles: IntArray): Int {
        val sortedPiles = piles.sorted()
        var result = 0
        val n = piles.size / 3
        repeat(n) {
            result += sortedPiles[n + it * 2]
        }
        return result
    }
}