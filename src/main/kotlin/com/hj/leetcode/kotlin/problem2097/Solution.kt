package com.hj.leetcode.kotlin.problem2097

/**
 * LeetCode page: [2097. Valid Arrangement of Pairs](https://leetcode.com/problems/valid-arrangement-of-pairs/);
 */
private typealias Node = Int
private typealias Edge = IntArray // (from, to)
private typealias AdjacencyList = Map<Node, MutableList<Edge>>
private typealias InDegree = Map<Node, Int>

class Solution {
    /* Complexity:
     * Time O(E) and Space O(E) where E is the size of pairs.
     */
    fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
        val (adjacencyList, inDegree) = adjacencyListAndInDegree(pairs)
        val start = startingNode(adjacencyList, inDegree)

        val dummy = intArrayOf()
        val result = Array(pairs.size) { dummy }
        var index = result.lastIndex // assign edges in reversed order

        postDFS(start, adjacencyList) {
            result[index] = it
            index--
        }
        return result
    }

    private fun adjacencyListAndInDegree(edges: Array<Edge>): Pair<AdjacencyList, InDegree> {
        val adjacencyList = mutableMapOf<Node, MutableList<Edge>>()
        val inDegree = mutableMapOf<Node, Int>()
        for (edge in edges) {
            val (u, v) = edge
            adjacencyList.computeIfAbsent(u) { mutableListOf() }.add(edge)
            inDegree.compute(v) { _, d -> (d ?: 0) + 1 }
        }
        return adjacencyList to inDegree
    }

    private fun startingNode(
        adjacencyList: AdjacencyList,
        inDegree: InDegree,
    ): Int =
        adjacencyList.keys.firstOrNull { node ->
            val outDegree = checkNotNull(adjacencyList[node]).size
            outDegree == (inDegree[node] ?: 0) + 1
        } ?: adjacencyList.keys.first()

    private fun postDFS(
        root: Int,
        adjacencyList: AdjacencyList,
        onEachEdge: (edge: Edge) -> Unit,
    ) {
        while (adjacencyList[root]?.isNotEmpty() == true) {
            val edge = checkNotNull(adjacencyList[root]).removeLast()
            postDFS(edge[1], adjacencyList, onEachEdge)
            onEachEdge(edge)
        }
    }
}
