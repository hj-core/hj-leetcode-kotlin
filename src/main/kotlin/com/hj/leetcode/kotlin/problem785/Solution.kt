package com.hj.leetcode.kotlin.problem785

/**
 * LeetCode page: [785. Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/);
 */
class Solution {
    /* Complexity:
     * Time O(N+E) and Space O(N) where N is the size of graph and E is the size of flattened graph;
     */
    fun isBipartite(graph: Array<IntArray>): Boolean {
        // Assign an initial, unspecified color to each node
        val nodes = graph.indices
        val nodeColors = MutableList(graph.size) { Color.Unspecified }

        /* Try to color each connected component in two colors. If we can do that, the graph
         * is bipartite; otherwise it is not.
         */
        for (node in nodes) {
            // If the node is colored, it belongs to one of the colored component
            val isColored = nodeColors[node] != Color.Unspecified
            if (isColored) {
                continue
            }

            // If the node is not colored, try to color the component it belongs to
            val canBeColored = colorConnectedComponent(node, Color.White, nodeColors, graph)
            if (!canBeColored) {
                return false
            }
        }
        return true
    }

    private enum class Color {
        White, Black, Unspecified
    }

    private fun colorConnectedComponent(
        source: Int,
        sourceColor: Color,
        nodeColors: MutableList<Color>,
        graph: Array<IntArray>
    ): Boolean {
        // If the node is colored, check if its color is correct
        val isColored = nodeColors[source] != Color.Unspecified
        if (isColored) {
            return nodeColors[source] == sourceColor
        }

        // If the node is not colored, color it and perform DFS to color the whole component
        nodeColors[source] = sourceColor
        val neighbourNodes = graph[source]
        val neighbourColor = oppositeColor(sourceColor)

        for (node in neighbourNodes) {
            val canBeColored = colorConnectedComponent(node, neighbourColor, nodeColors, graph)
            if (!canBeColored) {
                return false
            }
        }
        return true
    }

    private fun oppositeColor(color: Color): Color = when (color) {
        Color.White -> Color.Black
        Color.Black -> Color.White
        Color.Unspecified -> throw IllegalArgumentException()
    }
}