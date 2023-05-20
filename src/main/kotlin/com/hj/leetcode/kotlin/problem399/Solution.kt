package com.hj.leetcode.kotlin.problem399

/**
 * LeetCode page: [399. Evaluate Division](https://leetcode.com/problems/evaluate-division/);
 */
class Solution {
    /* Complexity:
     * Time O(NE) and Space O(E) where E is the size of equations and N is the size of queries;
     */
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val adjacency = hashMapOf<String, MutableList<AdjacentNode>>()
        for ((index, equation) in equations.withIndex()) {
            val (u, v) = equation
            val value = values[index]
            adjacency.computeIfAbsent(u) { mutableListOf() }.add(AdjacentNode(v, value))
            adjacency.computeIfAbsent(v) { mutableListOf() }.add(AdjacentNode(u, 1.0 / value))
        }

        return DoubleArray(queries.size) { index ->
            val (u, v) = queries[index]
            val unknownNodes = u !in adjacency || v !in adjacency
            if (unknownNodes) {
                return@DoubleArray -1.0
            }

            dfs(u, v, 1.0, adjacency) ?: -1.0
        }
    }

    private data class AdjacentNode(val id: String, val value: Double)

    private fun dfs(
        source: String,
        destination: String,
        weightProduct: Double,
        adjacency: Map<String, List<AdjacentNode>>,
        visited: MutableSet<String> = hashSetOf(),
    ): Double? {
        if (source == destination) {
            return weightProduct
        }

        visited.add(source)
        val adjacentNodes = adjacency[source] ?: emptyList()
        for (adjacentNode in adjacentNodes) {
            if (adjacentNode.id in visited) {
                continue
            }

            visited.add(adjacentNode.id)
            dfs(
                adjacentNode.id,
                destination,
                weightProduct * adjacentNode.value,
                adjacency,
                visited
            )?.let { return it }
        }

        return null
    }
}