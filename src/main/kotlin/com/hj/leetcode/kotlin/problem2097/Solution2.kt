package com.hj.leetcode.kotlin.problem2097

/**
 * LeetCode page: [2097. Valid Arrangement of Pairs](https://leetcode.com/problems/valid-arrangement-of-pairs/);
 */
private typealias Node2 = Int
private typealias Edge2 = IntArray // (from, to)
private typealias AdjacencyList2 = Map<Node2, MutableList<Node2>>
private typealias InDegree2 = Map<Node2, Int>

class Solution2 {
    /* Complexity:
     * Time O(E) and Space O(E) where E is the size of pairs.
     */
    fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
        // Hierholzer's Algorithm
        val (adjacencyList, inDegree) = adjacencyListAndInDegree(pairs)
        val start = startingNode(adjacencyList, inDegree)

        val dummy = intArrayOf()
        val result = Array(pairs.size) { dummy }
        var index = result.lastIndex // assign edges in reversed order

        val nodeStack = mutableListOf(start)
        while (0 <= index) { // we can use this condition because the problem said there is a walk
            val top = nodeStack.last()
            if (adjacencyList[top]?.isNotEmpty() == true) {
                val next = checkNotNull(adjacencyList[top]).removeLast()
                nodeStack.add(next)
            } else {
                nodeStack.removeLast()
                val from = nodeStack.last()
                result[index] = intArrayOf(from, top)
                index--
            }
        }
        return result
    }

    private fun adjacencyListAndInDegree(edges: Array<Edge2>): Pair<AdjacencyList2, InDegree2> {
        val adjacencyList = mutableMapOf<Node2, MutableList<Node2>>()
        val inDegree = mutableMapOf<Node2, Int>()
        for (edge in edges) {
            val (u, v) = edge
            adjacencyList.computeIfAbsent(u) { mutableListOf() }.add(v)
            inDegree.compute(v) { _, d -> (d ?: 0) + 1 }
        }
        return adjacencyList to inDegree
    }

    private fun startingNode(
        adjacencyList: AdjacencyList2,
        inDegree: InDegree2,
    ): Int =
        adjacencyList.keys.firstOrNull { node ->
            val outDegree = checkNotNull(adjacencyList[node]).size
            outDegree == (inDegree[node] ?: 0) + 1
        } ?: adjacencyList.keys.first()
}
