package com.hj.leetcode.kotlin.problem1235

import kotlin.math.max

/**
 * LeetCode page: [1235. Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of startTime, endTime and profit;
     */
    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val sortedJobs = MutableList(startTime.size) {
            Job(startTime[it], endTime[it], profit[it])
        }.apply { sortBy { it.start } }

        // dp[i]::= the maximum profit by scheduling the jobs in sortedJobs[i: ]
        val dp = IntArray(sortedJobs.size + 1)

        for (i in profit.indices.reversed()) {
            val profitIfSkip = dp[i + 1]
            val profitIfPick = sortedJobs[i].profit +
                    dp[sortedJobs.binarySearchLeftBy(sortedJobs[i].end) { it.start }]

            dp[i] = max(profitIfSkip, profitIfPick)
        }
        return dp.max()
    }

    private data class Job(val start: Int, val end: Int, val profit: Int)

    private fun <T, K : Comparable<K>> List<T>.binarySearchLeftBy(
        target: K,
        fromIndex: Int = 0,
        untilIndex: Int = size,
        selector: (T) -> K,
    ): Int {
        if (target <= selector(this[fromIndex])) {
            return fromIndex
        }
        if (target > selector(this[untilIndex - 1])) {
            return untilIndex
        }

        var left = fromIndex
        var right = untilIndex - 1
        while (left < right) {
            val mid = (left + right) ushr 1
            val value = selector(this[mid])
            if (value < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}