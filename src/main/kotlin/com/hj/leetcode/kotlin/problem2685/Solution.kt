package com.hj.leetcode.kotlin.problem2685

/**
 * LeetCode page: [2685. Count the Number of Complete Components](https://leetcode.com/problems/count-the-number-of-complete-components/);
 */
class Solution {
    fun countCompleteComponents(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        val solver = if (n < edges.size) UnionFindSolver() else DfsSolver()
        return solver.countCompleteComponents(n, edges)
    }
}

private interface Solver {
    fun countCompleteComponents(
        n: Int,
        edges: Array<IntArray>,
    ): Int

    fun isCompleteComponent(
        size: Int,
        totalEdges: Int,
    ): Boolean {
        require(size <= 50) { "Problem constraints is not met" }
        return size * (size - 1) / 2 == totalEdges
    }
}

private class UnionFindSolver : Solver {
    // Complexity:
    // Time O(n+E) and Space O(n) where E is the length of edges.
    override fun countCompleteComponents(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        val uf = UnionFind(n)
        val componentEdges = IntArray(n) // Total edges of the components. Only values at the uf root are valid.
        val componentSizes = IntArray(n) // Size of the components. Only values at the uf root are valid.

        for ((u, v) in edges) {
            uf.union(u, v)
            componentEdges[u]++
        }
        for (node in 0..<n) {
            val root = uf.find(node)
            if (root != node) {
                componentEdges[root] += componentEdges[node]
            }
            componentSizes[root]++
        }

        return (0..<n).count {
            componentSizes[it] > 0 && isCompleteComponent(componentSizes[it], componentEdges[it])
        }
    }
}

private class UnionFind(
    n: Int,
) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)

    fun find(x: Int): Int {
        if (x != parents[x]) {
            parents[x] = find(parents[x])
        }
        return parents[x]
    }

    fun union(
        x: Int,
        y: Int,
    ) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return

        when {
            ranks[rootX] < ranks[rootY] -> parents[rootX] = rootY
            ranks[rootX] > ranks[rootY] -> parents[rootY] = rootX
            else -> {
                parents[rootY] = rootX
                ranks[rootX]++
            }
        }
    }
}

private class DfsSolver : Solver {
    // Complexity:
    // Time O(n+E) and Space O(n+E) where E is the length of edges.
    override fun countCompleteComponents(
        n: Int,
        edges: Array<IntArray>,
    ): Int {
        val adjacencyList = adjacencyList(n, edges)
        val visited = BooleanArray(n)
        var result = 0
        for (node in 0..<n) {
            if (visited[node]) continue

            val sizeAndTwiceEdges = intArrayOf(0, 0)
            dfsComponent(node, adjacencyList, visited, sizeAndTwiceEdges)
            if (isCompleteComponent(sizeAndTwiceEdges[0], sizeAndTwiceEdges[1] / 2)) {
                result++
            }
        }
        return result
    }

    private fun adjacencyList(
        n: Int,
        edges: Array<IntArray>,
    ): List<List<Int>> {
        val result = List(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
            result[v].add(u)
        }
        return result
    }

    private fun dfsComponent(
        root: Int,
        adjacencyList: List<List<Int>>,
        visited: BooleanArray,
        sizeAndTwiceEdges: IntArray, // Output parameter
    ) {
        if (visited[root]) return

        visited[root] = true
        sizeAndTwiceEdges[0]++
        sizeAndTwiceEdges[1] += adjacencyList[root].size

        for (next in adjacencyList[root]) {
            dfsComponent(next, adjacencyList, visited, sizeAndTwiceEdges)
        }
    }
}
