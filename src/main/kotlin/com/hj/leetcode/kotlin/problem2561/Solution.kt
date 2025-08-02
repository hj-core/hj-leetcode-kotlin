package com.hj.leetcode.kotlin.problem2561

import kotlin.math.abs

/**
 * LeetCode page: [2561. Rearranging Fruits](https://leetcode.com/problems/rearranging-fruits/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of
    // basket1 and basket2.
    fun minCost(
        basket1: IntArray,
        basket2: IntArray,
    ): Long {
        var minCost = basket1[0]
        val netFreqs = hashMapOf<Int, Int>() // (cost, netFreq)

        for (cost in basket1) {
            minCost = minOf(minCost, cost)
            netFreqs.compute(cost) { _, v -> (v ?: 0) + 1 }
        }
        for (cost in basket2) {
            minCost = minOf(minCost, cost)
            netFreqs.compute(cost) { _, v -> if (v == 1) null else (v ?: 0) - 1 }
        }

        // Each operation fixes two bad pairs directly through
        // swapping them or indirectly through swapping with the
        // minCost.
        var neededOps = 0

        // The (cost, #BadPairs) where cost < minCost*2
        val costPairs = mutableListOf<IntArray>()

        for ((cost, netFreq) in netFreqs) {
            if (netFreq and 1 == 1) {
                return -1L
            }
            val pairs = abs(netFreq) / 2
            neededOps += pairs
            if (cost < minCost * 2) {
                costPairs += intArrayOf(cost, pairs)
            }
        }
        neededOps /= 2
        costPairs.sortWith { o1, o2 -> o1[0] - o2[0] } // Sort by cost

        var result = 0L
        var i = 0
        while (0 < neededOps && i < costPairs.size) {
            val ops = minOf(neededOps, costPairs[i][1])
            result += ops.toLong() * costPairs[i][0]
            neededOps -= ops
            i++
        }
        result += 2L * minCost * neededOps
        return result
    }
}
