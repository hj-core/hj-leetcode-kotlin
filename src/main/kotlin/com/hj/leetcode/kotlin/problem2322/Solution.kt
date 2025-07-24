package com.hj.leetcode.kotlin.problem2322

/**
 * LeetCode page: [2322. Minimum Score After Removals on a Tree](https://leetcode.com/problems/minimum-score-after-removals-on-a-tree/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of
    // nums.
    fun minimumScore(
        nums: IntArray,
        edges: Array<IntArray>,
    ): Int {
        val (reachTime, leaveTime, subtreeXors) = dfs(nums, edges)

        var result = Int.MAX_VALUE
        for (i in 1..<nums.size) {
            for (j in i + 1..<nums.size) {
                var xor1 = subtreeXors[0]
                var xor2 = subtreeXors[i]
                var xor3 = subtreeXors[j]

                if (inTree(i, j, reachTime, leaveTime)) {
                    xor1 = xor1 xor xor2
                    xor2 = xor2 xor xor3
                } else if (inTree(j, i, reachTime, leaveTime)) {
                    xor1 = xor1 xor xor3
                    xor3 = xor3 xor xor2
                } else {
                    xor1 = xor1 xor xor2 xor xor3
                }

                val score = maxOf(xor1, xor2, xor3) - minOf(xor1, xor2, xor3)
                result = minOf(result, score)
            }
        }
        return result
    }

    // Returns the reachTime, leaveTime, and subtreeXors for
    // the tree rooted at 0, where reachTime[i] and leaveTime[i]
    // are the times when the DFS reaches and leaves node i,
    // and subtreeXors[i] is the XOR of all node values within
    // the subtree rooted at node i.
    private fun dfs(
        nums: IntArray,
        edges: Array<IntArray>,
    ): Array<IntArray> {
        val adjacencyList = buildAdjacencyList(edges)
        var time = 0
        val reachTime = IntArray(nums.size)
        val leaveTime = IntArray(nums.size)
        val subtreeXors = IntArray(nums.size)

        fun dfsHelper(
            node: Int,
            parent: Int,
        ) {
            reachTime[node] = time
            time++
            subtreeXors[node] = nums[node]

            for (child in adjacencyList[node]) {
                if (child == parent) {
                    continue
                }
                dfsHelper(child, node)
                subtreeXors[node] = subtreeXors[node] xor subtreeXors[child]
            }
            leaveTime[node] = time
            time++
        }

        dfsHelper(0, -1)
        return arrayOf(reachTime, leaveTime, subtreeXors)
    }

    private fun buildAdjacencyList(edges: Array<IntArray>): List<List<Int>> {
        val result = List(edges.size + 1) { mutableListOf<Int>() }
        for ((a, b) in edges) {
            result[a].add(b)
            result[b].add(a)
        }
        return result
    }

    // Returns whether node is a node in root.
    private fun inTree(
        root: Int,
        node: Int,
        reachTime: IntArray,
        leaveTime: IntArray,
    ): Boolean = reachTime[root] < reachTime[node] && leaveTime[node] < leaveTime[root]
}
