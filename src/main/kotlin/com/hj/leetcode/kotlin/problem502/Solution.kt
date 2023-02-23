package com.hj.leetcode.kotlin.problem502

import java.util.*

/**
 * LeetCode page: [502. IPO](https://leetcode.com/problems/ipo/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of capital/profits;
     */
    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        // Use the index of capital/profits as project's id
        val idSortedByCapital = capital.indices.sortedBy { capital[it] }
        var maxCapital = w
        var nextIdIndex = 0
        val availableProfitMaxPq = PriorityQueue<Int>(reverseOrder())

        repeat(k) {
            while (
                nextIdIndex < idSortedByCapital.size &&
                capital[idSortedByCapital[nextIdIndex]] <= maxCapital
            ) {
                val id = idSortedByCapital[nextIdIndex]
                val profit = profits[id]
                availableProfitMaxPq.offer(profit)
                nextIdIndex++
            }

            val maxAvailableProfit = availableProfitMaxPq.poll() ?: 0
            val noAvailableProfitOrIsZeroProfit = maxAvailableProfit == 0
            if (noAvailableProfitOrIsZeroProfit) return maxCapital
            maxCapital += maxAvailableProfit
        }
        return maxCapital
    }
}