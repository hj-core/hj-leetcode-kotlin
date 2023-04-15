package com.hj.leetcode.kotlin.problem2218

/**
 * LeetCode page: [2218. Maximum Value of K Coins From Piles](https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/);
 */
class Solution {
    fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
        // subResults[i][j] ::= the max value by picking j coins from piles up to index i
        val subResults = subResultHolder(piles, k).apply {
            updateBaseCases(piles, k, this)
            updateRemainingCases(piles, k, this)
        }
        return originalProblem(piles, k, subResults)
    }

    private fun subResultHolder(piles: List<List<Int>>, k: Int): Array<IntArray> {
        return Array(piles.size) { IntArray(k + 1) }
    }

    private fun updateBaseCases(piles: List<List<Int>>, k: Int, subResultHolder: Array<IntArray>) {
        // Without taking any coins, we get zero max value
        for (end in piles.indices) {
            subResultHolder[end][0] = 0
        }

        // Max value if we can only pick coins from the first pile
        var sumCurrentCoins = 0
        for (numCoins in 1..k) {
            sumCurrentCoins += piles[0].getOrElse(numCoins - 1) { 0 }
            subResultHolder[0][numCoins] = sumCurrentCoins
        }
    }

    private fun updateRemainingCases(piles: List<List<Int>>, k: Int, subResultHolder: Array<IntArray>) {
        for (end in 1 until piles.size) {
            for (numCoins in 1..k) {
                var prefixSumEndPile = 0
                for (numEndPileCoins in 0..numCoins) {
                    prefixSumEndPile += piles[end].getOrElse(numEndPileCoins - 1) { 0 }
                    subResultHolder[end][numCoins] = maxOf(
                        subResultHolder[end][numCoins],
                        prefixSumEndPile + subResultHolder[end - 1][numCoins - numEndPileCoins]
                    )
                }
            }
        }
    }

    private fun originalProblem(piles: List<List<Int>>, k: Int, subResultHolder: Array<IntArray>): Int {
        return subResultHolder[piles.lastIndex][k]
    }
}