package com.hj.leetcode.kotlin.problem518

/**
 * LeetCode page: [518. Coin Change II](https://leetcode.com/problems/coin-change-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(amount * N) and Space O(amount * N) where N is the size of coins;
     */
    fun change(amount: Int, coins: IntArray): Int {
        return changeBySuffixCoins(amount, coins, 0, hashMapOf())
    }

    private fun changeBySuffixCoins(
        amount: Int,
        coins: IntArray,
        suffixStart: Int,
        memoization: MutableMap<Pair<Int, Int>, Int> // key = Pair(amount, suffixStart)
    ): Int {
        if (amount == 0) {
            return 1
        }
        if (amount < 0 || suffixStart >= coins.size) {
            return 0
        }

        val key = Pair(amount, suffixStart)
        if (key in memoization) {
            return checkNotNull(memoization[key])
        }

        val result = (changeBySuffixCoins(amount, coins, suffixStart + 1, memoization)
                + changeBySuffixCoins(amount - coins[suffixStart], coins, suffixStart, memoization))
        memoization[key] = result
        return result
    }
}