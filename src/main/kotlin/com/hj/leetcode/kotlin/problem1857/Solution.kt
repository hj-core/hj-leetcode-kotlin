package com.hj.leetcode.kotlin.problem1857

/**
 * LeetCode page: [1857. Largest Color Value in a Directed Graph](https://leetcode.com/problems/largest-color-value-in-a-directed-graph/);
 */
class Solution {
    // Complexity:
    // Time O(NS+ES) and Space O(NS+E) where N is the length of colors, E is
    // the length of edges, and S is the number of possible colors.
    fun largestPathValue(
        colors: String,
        edges: Array<IntArray>,
    ): Int {
        val n = colors.length
        val adjacencyList = computeAdjacencyList(n, edges)

        // dp[i][c-'a'] ::= the maximum color frequency of c among all paths
        // starting from node i.
        val dp = Array(n) { IntArray(26) }

        // seen[i] ::= whether dp[i] has been settled. 0 for not yet, 1 for
        // in process, 2 for done.
        val seen = IntArray(n)

        var result = 0
        for (node in 0..<n) {
            if (seen[node] != 0) {
                continue
            }
            if (dfs(node, colors, adjacencyList, seen, dp)) {
                return -1
            }
            for (freq in dp[node]) {
                result = maxOf(result, freq)
            }
        }
        return result
    }

    private fun computeAdjacencyList(
        n: Int,
        edges: Array<IntArray>,
    ): List<List<Int>> {
        val result = List(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            result[u].add(v)
        }
        return result
    }

    // `dfs` updates the seen and dp for node recursively and returns whether
    // a cycle has been detected.
    private fun dfs(
        node: Int,
        colors: String,
        adjacencyList: List<List<Int>>,
        seen: IntArray,
        dp: Array<IntArray>,
    ): Boolean {
        if (seen[node] == 1) {
            return true
        }
        if (seen[node] == 2) {
            return false
        }

        seen[node] = 1
        for (neighbour in adjacencyList[node]) {
            if (dfs(neighbour, colors, adjacencyList, seen, dp)) {
                return true
            }

            for (i in 0..<26) {
                dp[node][i] = maxOf(dp[node][i], dp[neighbour][i])
            }
        }
        dp[node][colors[node] - 'a']++
        seen[node] = 2
        return false
    }
}
