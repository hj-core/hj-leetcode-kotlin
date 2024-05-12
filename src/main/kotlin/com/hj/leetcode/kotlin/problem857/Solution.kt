package com.hj.leetcode.kotlin.problem857

import java.util.*
import kotlin.math.min

/**
 * LeetCode page: [857. Minimum Cost to Hire K Workers](https://leetcode.com/problems/minimum-cost-to-hire-k-workers/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of quality;
     */
    fun mincostToHireWorkers(quality: IntArray, wage: IntArray, k: Int): Double {
        val sortedWorkers = quality
            .indices
            .sortedBy { wage[it].toDouble() / quality[it] }

        // Store the k-1 worst quality workers during iteration
        val qualityPq = PriorityQueue<Int>(reverseOrder())
        var sumQualityPq = 0

        for (i in 0..<k - 1) {
            val worker = sortedWorkers[i]
            qualityPq.offer(quality[worker])
            sumQualityPq += quality[worker]
        }
        // Initialize with the cost of the first k workers in the sorted workers
        var result = (wage[sortedWorkers[k - 1]]
                + (wage[sortedWorkers[k - 1]].toDouble() / quality[sortedWorkers[k - 1]]) * sumQualityPq)

        // Find the min cost of each possible unit quality wage and update result
        for (i in k - 1..<sortedWorkers.size) {
            val worker = sortedWorkers[i]
            val unitQualityWage = wage[worker].toDouble() / quality[worker]
            val cost = wage[worker] + unitQualityWage * sumQualityPq

            result = min(result, cost)
            qualityPq.offer(quality[worker])
            sumQualityPq += quality[worker] - qualityPq.poll()
        }
        return result
    }
}