package com.hj.leetcode.kotlin.problem3633

/**
 * LeetCode page: [3633. Earliest Finish Time for Land and Water Rides I](https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-i/);
 */
class Solution {
    // Complexity:
    // Time O(M+N) and Space O(1) where M and N are the length of landStartTime
    // and waterStartTime respectively.
    fun earliestFinishTime(
        landStartTime: IntArray,
        landDuration: IntArray,
        waterStartTime: IntArray,
        waterDuration: IntArray,
    ): Int =
        minOf(
            computeMinFinish(landStartTime, landDuration, waterStartTime, waterDuration),
            computeMinFinish(waterStartTime, waterDuration, landStartTime, landDuration),
        )

    // Returns the minimum finish time if we go activity 1 first, followed by activity 2.
    private fun computeMinFinish(
        startTime1: IntArray,
        duration1: IntArray,
        startTime2: IntArray,
        duration2: IntArray,
    ): Int {
        var minFinish1 = startTime1[0] + duration1[0]
        for (i in 1..<startTime1.size) {
            val finish1 = startTime1[i] + duration1[i]
            minFinish1 = minOf(minFinish1, finish1)
        }

        var minFinish2 = maxOf(startTime2[0], minFinish1) + duration2[0]
        for (i in 1..<startTime2.size) {
            val finish2 = maxOf(startTime2[i], minFinish1) + duration2[i]
            minFinish2 = minOf(minFinish2, finish2)
        }

        return minFinish2
    }
}
