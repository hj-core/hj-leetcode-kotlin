package com.hj.leetcode.kotlin.problem2493

/**
 * LeetCode page: [2493. Divide Nodes Into the Maximum Number of Groups](https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/);
 */
class Solution {
    /* Complexity:
     * Time O(nE) and Space O(n+E) where E is the length of edges.
     */
    fun magnificentSets(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        val neighbours = neighbours(n, edges)
        if (hasOddLengthCycle(n, neighbours)) {
            return -1
        }

        // componentIds[node] ::=
        // the ID of the component to which this node belongs; a negative ID means undefined.
        val componentIds = IntArray(n + 1) { -1 }
        // subResults[componentId]::= the sub result for this component id
        val subResults = mutableListOf<Int>()

        val bfsQueue = ArrayDeque<Int>()
        val visited = IntArray(n + 1)

        for (node in 1..n) {
            if (componentIds[node] < 0) {
                componentIds[node] = subResults.size
                subResults.add(0)
            }
            val id = componentIds[node]
            var nextDepth = 0
            bfsQueue.add(node)
            visited[node]++

            while (bfsQueue.isNotEmpty()) {
                nextDepth++
                repeat(bfsQueue.size) {
                    val curr = bfsQueue.removeFirst()
                    for (next in neighbours[curr]) {
                        if (visited[next] < visited[node]) {
                            componentIds[next] = id
                            bfsQueue.add(next)
                            visited[next]++
                        }
                    }
                }
            }
            subResults[id] = maxOf(subResults[id], nextDepth)
        }
        return subResults.sum()
    }

    private fun neighbours(
        n: Int,
        edges: Array<IntArray>,
    ): List<List<Int>> {
        val result = List(n + 1) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
            result[v].add(u)
        }
        return result
    }

    private fun hasOddLengthCycle(
        n: Int,
        neighbours: List<List<Int>>,
    ): Boolean {
        // Check each component to see whether it can be two-colored.
        // colors[node] ::= {0: red, 1: black, 2: undefined}
        val colors = IntArray(n + 1) { 2 }
        for (node in 1..n) {
            if (colors[node] != 2) {
                continue
            }
            colors[node] = 0
            if (!colorComponent(node, neighbours, colors)) {
                return true
            }
        }
        return false
    }

    // Attempts to two-color the component and returns whether the attempt was successful.
    private fun colorComponent(
        src: Int,
        neighbours: List<List<Int>>,
        colors: IntArray, // colors[node] ::= {0: red, 1: black, 2: undefined}
    ): Boolean {
        for (next in neighbours[src]) {
            if (colors[src] == colors[next]) {
                return false
            }
            if (colors[next] != 2) {
                continue
            }
            colors[next] = colors[src] xor 1
            if (!colorComponent(next, neighbours, colors)) {
                return false
            }
        }
        return true
    }
}
