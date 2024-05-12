package com.hj.leetcode.kotlin.problem1584

import kotlin.math.abs
import kotlin.math.min

/**
 * LeetCode page: [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of points;
     */
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        // Apply Prim's algorithm and growing the MST from node 0;

        /* The minimum cost for each remaining node to directly connect to
         * the growing MST.
         */
        val minCostToTree = hashMapOf<Int, Int>().apply {
            for (i in points.indices) {
                this[i] = Int.MAX_VALUE
            }
            this[0] = 0
        }

        var result = 0
        while (minCostToTree.isNotEmpty()) {
            val bestNode = minCostToTree
                .keys
                .minBy { checkNotNull(minCostToTree[it]) }

            result += checkNotNull(minCostToTree[bestNode])
            minCostToTree.remove(bestNode)

            for ((node, cost) in minCostToTree) {
                minCostToTree[node] = min(cost, manhattanDistance(points[node], points[bestNode]))
            }
        }
        return result
    }

    private fun manhattanDistance(pt1: IntArray, pt2: IntArray): Int {
        return abs(pt1[0] - pt2[0]) + abs(pt1[1] - pt2[1])
    }
}