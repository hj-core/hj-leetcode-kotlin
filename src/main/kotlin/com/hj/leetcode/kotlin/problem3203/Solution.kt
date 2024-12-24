package com.hj.leetcode.kotlin.problem3203

import java.util.*

/**
 * LeetCode page: [3203. Find Minimum Diameter After Merging Two Trees](https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/);
 */
class Solution {
    /* Complexity:
     * Time O(E1+E2) and Space O(E1+E2)
     * where E1 and E2 are the size of edges1 and edges2, respectively.
     */
    fun minimumDiameterAfterMerge(
        edges1: Array<IntArray>,
        edges2: Array<IntArray>,
    ): Int {
        val diameter1 = dfs(0, -1, adjacencyList(edges1))[0]
        val diameter2 = dfs(0, -1, adjacencyList(edges2))[0]
        return maxOf(
            diameter1,
            diameter2,
            1 + (diameter1 + 1) / 2 + (diameter2 + 1) / 2,
        )
    }

    // Return the diameter of root and the longest path starting from root
    private fun dfs(
        root: Int,
        parentNode: Int,
        adjacencyList: Map<Int, List<Int>>,
    ): IntArray {
        var diameter = 0
        val twoMaxChildPath = PriorityQueue<Int>()

        for (child in adjacencyList[root] ?: emptyList()) {
            if (child == parentNode) {
                continue
            }
            val (childDiameter, maxPathFromChild) = dfs(child, root, adjacencyList)
            diameter = maxOf(diameter, childDiameter)

            twoMaxChildPath.offer(maxPathFromChild)
            if (twoMaxChildPath.size > 2) {
                twoMaxChildPath.poll()
            }
        }
        var maxPathFromRoot = 0
        var maxPathPassRoot = 0
        while (twoMaxChildPath.isNotEmpty()) {
            val childLength = twoMaxChildPath.poll()
            maxPathPassRoot += 1 + childLength
            maxPathFromRoot = maxOf(maxPathFromRoot, 1 + childLength)
        }
        diameter = maxOf(diameter, maxPathPassRoot)
        return intArrayOf(diameter, maxPathFromRoot)
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, List<Int>> {
        val result = mutableMapOf<Int, MutableList<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { mutableListOf() }.add(v)
            result.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return result
    }
}
