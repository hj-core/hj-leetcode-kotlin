package com.hj.leetcode.kotlin.problem399

/**
 * LeetCode page: [399. Evaluate Division](https://leetcode.com/problems/evaluate-division/);
 */
class Solution {
    /* Complexity:
     * Time O(NE) and Space O(E) where E is the size of equations and N is the size of queries;
     */
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val adjacency = buildAdjacencyList(equations, values)

        return DoubleArray(queries.size) { index ->
            val (u, v) = queries[index]

            if (u !in adjacency || v !in adjacency) {
                -1.0
            } else {
                dfs(u, v, 1.0, adjacency) ?: -1.0
            }
        }
    }

    private fun buildAdjacencyList(
        equations: List<List<String>>,
        values: DoubleArray,
    ): Map<String, List<AdjacentNode>> {
        val result = hashMapOf<String, MutableList<AdjacentNode>>()
        for ((index, equation) in equations.withIndex()) {
            val (u, v) = equation
            val value = values[index]
            result.computeIfAbsent(u) { mutableListOf() }.add(AdjacentNode(v, value))
            result.computeIfAbsent(v) { mutableListOf() }.add(AdjacentNode(u, 1.0 / value))
        }
        return result
    }

    private data class AdjacentNode(val id: String, val multiple: Double)

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
                weightProduct * adjacentNode.multiple,
                adjacency,
                visited
            )?.let { return it }
        }
        return null
    }
}