package com.hj.leetcode.kotlin.problem826

import kotlin.math.max

/**
 * LeetCode page: [826. Most Profit Assigning Work](https://leetcode.com/problems/most-profit-assigning-work/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+MLogM) and Space O(N+M) where N is the size of difficulty
     * and M is the size of worker;
     */
    fun maxProfitAssignment(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
        val sortedJobs = difficulty
            .indices
            .mapTo(mutableListOf()) { Job(difficulty[it], profit[it]) }
            .apply { sortBy { it.difficulty } }
        val sortedWorkers = worker.sorted()

        var result = 0
        var jobIndex = 0
        var bestProfit = 0
        for (ability in sortedWorkers) {
            while (jobIndex < sortedJobs.size && sortedJobs[jobIndex].difficulty <= ability) {
                bestProfit = max(bestProfit, sortedJobs[jobIndex].profit)
                jobIndex++
            }
            result += bestProfit
        }
        return result
    }

    private data class Job(val difficulty: Int, val profit: Int)
}