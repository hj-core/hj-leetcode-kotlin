package com.hj.leetcode.kotlin.problem2467

/**
 * LeetCode page: [2467. Most Profitable Path in a Tree](https://leetcode.com/problems/most-profitable-path-in-a-tree/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the size of `edges`.
    fun mostProfitablePath(
        edges: Array<IntArray>,
        bob: Int,
        amount: IntArray,
    ): Int =
        dfs(
            0,
            -1,
            adjacencyList(edges.size + 1, edges),
            0,
            bob,
            amount,
        ).first

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

    // `dfs` explores from the root, excluding the edge to `parentNode`, and returns the maximum income
    // Alice can have along with the distance from the root to Bob.
    private fun dfs(
        root: Int,
        parentNode: Int,
        adjacencyList: List<List<Int>>,
        stepsToAlice: Int,
        bob: Int,
        amount: IntArray,
    ): Pair<Int, Int> {
        var stepsToBob = if (root == bob) 0 else -1
        var maxChildScore =
            if (isLeaf(root, parentNode, adjacencyList)) {
                0
            } else {
                Int.MIN_VALUE
            }

        for (child in adjacencyList[root]) {
            if (child == parentNode) {
                continue
            }
            val (childScore, childStepsToBob) =
                dfs(
                    child,
                    root,
                    adjacencyList,
                    stepsToAlice + 1,
                    bob,
                    amount,
                )

            if (childStepsToBob != -1) {
                stepsToBob = childStepsToBob + 1
            }
            maxChildScore = maxOf(maxChildScore, childScore)
        }
        val maxScore =
            when {
                stepsToBob == -1 -> maxChildScore + amount[root]
                stepsToAlice < stepsToBob -> maxChildScore + amount[root]
                stepsToAlice > stepsToBob -> maxChildScore
                else -> maxChildScore + amount[root] / 2
            }
        return Pair(maxScore, stepsToBob)
    }

    private fun isLeaf(
        node: Int,
        parentNode: Int,
        adjacencyList: List<List<Int>>,
    ): Boolean {
        val children = adjacencyList[node]
        return children.isEmpty() || (children.size == 1 && children[0] == parentNode)
    }
}
