package com.hj.leetcode.kotlin.problem1557

/**
 * LeetCode page: [1557. Minimum Number of Vertices to Reach All Nodes](https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/);
 */
class Solution {
    /* Complexity:
     * Time O(n+E) and Space O(n) where E is the size of edges;
     */
    fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
        return zeroInDegreeNodes(n, nodeInDegrees(n, edges))
    }

    private fun zeroInDegreeNodes(n: Int, nodeInDegrees: IntArray): List<Int> {
        val nodes = 0 until n
        return nodes.filter { nodeInDegrees[it] == 0 }
    }

    private fun nodeInDegrees(n: Int, edges: List<List<Int>>): IntArray {
        // result[i] ::= the in-degree of node i
        val result = IntArray(n).apply {
            for ((_, v) in edges) {
                this[v]++
            }
        }
        return result
    }
}