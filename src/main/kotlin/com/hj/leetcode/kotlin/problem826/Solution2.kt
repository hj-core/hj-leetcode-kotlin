package com.hj.leetcode.kotlin.problem826

import kotlin.math.max

/**
 * LeetCode page: [826. Most Profit Assigning Work](https://leetcode.com/problems/most-profit-assigning-work/);
 */
class Solution2 {
    /* Complexity:
     * Time O(M+N+K) and Space O(K) where M is the length of difficulty and profit,
     * N is the length of worker and K is the maximum ability of worker;
     */
    fun maxProfitAssignment(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
        val maxAbility = worker.max()
        val maxProfitMemo = IntArray(maxAbility + 1)
        for ((jobDifficult, jobProfit) in difficulty.asSequence().zip(profit.asSequence()))  {
            if (jobDifficult < maxProfitMemo.size) {
                maxProfitMemo[jobDifficult] = max(maxProfitMemo[jobDifficult], jobProfit)
            }
        }
        for (i in 1..<maxProfitMemo.size) {
            maxProfitMemo[i] = max(maxProfitMemo[i], maxProfitMemo[i - 1])
        }

        return worker.sumOf { maxProfitMemo[it] }
    }
}