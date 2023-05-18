package com.hj.leetcode.kotlin.problem1557

/**
 * LeetCode page: [1557. Minimum Number of Vertices to Reach All Nodes](https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/);
 */
class Solution {
    /* Complexity:
     * Time O(n+E) and Space O(n) where E is the size of edges;
     */
    fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
        // Find the in-degree of each node, i.e. nodeInDegree[i] ::= the in-degree of node i
        val nodeInDegree = nodeInDegree(n, edges)

        // The smallest set consists of those vertices with zero in-degree
        val nodes = 0 until n
        return nodes.filter { nodeInDegree[it] == 0 }
    }

    private fun nodeInDegree(n: Int, edges: List<List<Int>>): IntArray {
        val nodeInDegree = IntArray(n)
        for ((_, v) in edges) {
            nodeInDegree[v]++
        }
        return nodeInDegree
    }
}