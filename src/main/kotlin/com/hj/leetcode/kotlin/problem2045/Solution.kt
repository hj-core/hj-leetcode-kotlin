package com.hj.leetcode.kotlin.problem2045

/**
 * LeetCode page: [2045. Second Minimum Time to Reach Destination](https://leetcode.com/problems/second-minimum-time-to-reach-destination/);
 */
class Solution {
    /* Complexity:
     * Time O(n+E) and Space O(n+E) where E is the size of edges;
     */
    fun secondMinimum(n: Int, edges: Array<IntArray>, time: Int, change: Int): Int {
        return traverseTime(secondShortestPathLength(edges, n), time, change)
    }

    private fun secondShortestPathLength(edges: Array<IntArray>, n: Int): Int {
        val adjacencyList = adjacencyList(edges)
        val bfsQueue = ArrayDeque<Int>()
        val minDepth = IntArray(n + 1) { -1 } // min depth from 1 to each node
        val secondMinDepth = IntArray(n + 1) { -1 } // strict second min depth from 1 to each node

        var depth = 0 // depth of nodes at the start of each while-loop
        bfsQueue.addLast(1)
        minDepth[1] = 0
        while (bfsQueue.isNotEmpty() && secondMinDepth[n] == -1) {
            depth++
            repeat(bfsQueue.size) {
                val node = bfsQueue.removeFirst()
                for (neighbour in (adjacencyList[node] ?: emptySet())) {
                    when {
                        minDepth[neighbour] == -1 -> {
                            minDepth[neighbour] = depth
                            bfsQueue.addLast(neighbour)
                        }

                        secondMinDepth[neighbour] == -1 && minDepth[neighbour] != depth -> {
                            secondMinDepth[neighbour] = depth
                            bfsQueue.addLast(neighbour)
                        }
                    }
                }
            }
        }
        /* Must exist and at most minDepth[n]+2 because the graph is connected
         * and allow multiple traverses of same edge
         */
        return secondMinDepth[n]
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, Set<Int>> {
        val result = mutableMapOf<Int, MutableSet<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { mutableSetOf() }.add(v)
            result.computeIfAbsent(v) { mutableSetOf() }.add(u)
        }
        return result
    }

    private fun traverseTime(pathLength: Int, time: Int, change: Int): Int {
        var result = 0
        repeat(pathLength) {
            val numChanged = result / change
            val isRed = numChanged % 2 == 1
            if (isRed) {
                result = change * (numChanged + 1)
            }
            result += time
        }
        return result
    }
}