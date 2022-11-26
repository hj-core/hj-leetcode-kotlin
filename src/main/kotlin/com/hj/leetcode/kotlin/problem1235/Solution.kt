package com.hj.leetcode.kotlin.problem1235

import java.util.*

/**
 * LeetCode page: [1235. Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of startTime/endTime/profit, i.e. #jobs;
     */
    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val sortedIndices = endTime.indices.sortedBy { endTime[it] }
        val maxProfitAtTime = TreeMap<Int, Int>().apply { put(0, 0) } // entry = Pair(time, maxProfit)

        for (index in sortedIndices) {
            val jobStart = startTime[index]
            val jobEnd = endTime[index]
            val jobProfit = profit[index]

            val maxProfitBeforeJobStart = maxProfitAtTime.floorEntry(jobStart).value
            val maxProfitWithJob = jobProfit + maxProfitBeforeJobStart
            val hasMoreProfitThanPrev = maxProfitWithJob > maxProfitAtTime.lastEntry().value

            if (hasMoreProfitThanPrev) {
                maxProfitAtTime[jobEnd] = maxProfitWithJob
            }
        }
        return maxProfitAtTime.lastEntry().value
    }
}