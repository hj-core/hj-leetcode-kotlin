package com.hj.leetcode.kotlin.problem3559

/**
 * LeetCode page: [3559. Number of Ways to Assign Edge Weights II](https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(N+M) where N and M are the length of edges and
    // queries, respectively.
    fun assignEdgeWeights(
        edges: Array<IntArray>,
        queries: Array<IntArray>,
    ): IntArray {
        val n = edges.size + 1
        val depth = IntArray(n + 1)
        val adjacencyList = buildAdjacencyList(n, edges)
        val ancestor = IntArray(n + 1)
        val nodeQueries = buildNodeQueries(queries)
        val answers = IntArray(queries.size)

        depth[1] = 1 // Assume a virtual node 0
        dfsDepthAndAnswers(
            1,
            adjacencyList,
            depth,
            ancestor,
            nodeQueries,
            answers,
        )

        return answers
    }

    private fun buildAdjacencyList(
        n: Int,
        edges: Array<IntArray>,
    ): List<List<Int>> {
        val adjList = List(n + 1) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            adjList[u].add(v)
            adjList[v].add(u)
        }

        return adjList
    }

    private fun buildNodeQueries(
        queries: Array<IntArray>,
    ): HashMap<Int, MutableList<Pair<Int, Int>>> {
        val indices = HashMap<Int, MutableList<Pair<Int, Int>>>()
        for (i in queries.indices) {
            val (u, v) = queries[i]
            indices.computeIfAbsent(u) { mutableListOf() }.add(Pair(i, v))
            indices.computeIfAbsent(v) { mutableListOf() }.add(Pair(i, u))
        }

        return indices
    }

    private fun dfsDepthAndAnswers(
        node: Int,
        adjacencyList: List<List<Int>>,
        depth: IntArray,
        ancestor: IntArray,
        nodeQueries: HashMap<Int, MutableList<Pair<Int, Int>>>,
        answers: IntArray,
    ) {
        ancestor[node] = node
        for (next in adjacencyList[node]) {
            if (depth[next] == 0) {
                depth[next] = depth[node] + 1
                dfsDepthAndAnswers(next, adjacencyList, depth, ancestor, nodeQueries, answers)
                ancestor[next] = node
            }
        }

        // We are performing post-order traversal. For a visited 'other' node,
        // `compressFind(other)` will return 'node' if 'other' is a descendant of
        // 'node'. Otherwise, `compressFind(other)` will return the lowest common
        // ancestor of 'node' and 'other'.
        for ((queryIndex, other) in nodeQueries[node] ?: emptyList()) {
            if (depth[other] > 0) {
                val lca = compressFind(ancestor, other)
                val distance = depth[node] + depth[other] - 2 * depth[lca]
                answers[queryIndex] = computeWays(distance)
            }
        }
    }

    private fun compressFind(
        ancestor: IntArray,
        node: Int,
    ): Int {
        if (ancestor[node] != node) {
            ancestor[node] = compressFind(ancestor, ancestor[node])
        }
        return ancestor[node]
    }

    // Returns 2^(distance - 1) % 1_000_000_007 where distance >= 0.
    private fun computeWays(distance: Int): Int {
        require(distance >= 0)
        if (distance == 0) {
            return 0
        }

        val modulus = 1_000_000_007
        var ways = 1L
        var base = 2L
        var pow = distance - 1
        while (pow > 0) {
            if (pow and 1 == 1) {
                ways = (ways * base) % modulus
            }
            base = (base * base) % modulus
            pow = pow shr 1
        }

        return ways.toInt()
    }
}
