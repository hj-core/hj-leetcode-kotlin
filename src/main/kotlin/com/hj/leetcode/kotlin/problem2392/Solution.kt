package com.hj.leetcode.kotlin.problem2392

/**
 * LeetCode page: [2392. Build a Matrix With Conditions](https://leetcode.com/problems/build-a-matrix-with-conditions/);
 */
class Solution {
    /* Complexity:
     * Time O(k^2+M+N) and Space O(k^2+M+N) where M is the size of rowConditions
     * and N is the size of colConditions;
     */
    fun buildMatrix(k: Int, rowConditions: Array<IntArray>, colConditions: Array<IntArray>): Array<IntArray> {
        val rowIndices = topologicallySorted(k, rowConditions)
        if (rowIndices[0] != k) {
            return emptyArray()
        }

        val colIndices = topologicallySorted(k, colConditions)
        if (colIndices[0] != k) {
            return emptyArray()
        }

        val result = Array(k) { IntArray(k) }
        for (node in 1..k) {
            result[rowIndices[node]][colIndices[node]] = node
        }
        return result
    }

    /** Given a graph that consists of nodes 1 to k and directed edges in format (from, to),
     * return a topologically sorted order of the nodes.
     *
     * The value at index 0 is a flag that if it does not equal to k,
     * there is a cycle in the graph, and thus a topologically sorted order does not exist.
     *
     * The value at each index from 1 to k represents the position (zero-based) of
     * the corresponding node from 1 to k in the sorted order.
     */
    private fun topologicallySorted(k: Int, edges: Array<IntArray>): IntArray {
        val inDegrees = getInDegrees(k, edges)
        val adjacencyList = getAdjacencyList(k, edges)

        val result = IntArray(k + 1) { -1 }
        var order = 0
        val nodeQueue = ArrayDeque<Int>().apply {
            for (node in 1..k) {
                if (inDegrees[node] == 0) {
                    this.addLast(node)
                }
            }
        }

        while (nodeQueue.isNotEmpty()) {
            val node = nodeQueue.removeFirst()
            result[node] = order
            order++

            for (next in adjacencyList[node]) {
                inDegrees[next]--
                if (inDegrees[next] == 0) {
                    nodeQueue.addLast(next)
                }
            }
        }
        result[0] = order
        return result
    }

    private fun getInDegrees(k: Int, edges: Array<IntArray>): IntArray {
        return IntArray(k + 1).apply {
            for ((_, v) in edges) {
                this[v]++
            }
        }
    }

    private fun getAdjacencyList(k: Int, edges: Array<IntArray>): List<List<Int>> {
        val result = List(k + 1) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
        }
        return result
    }
}