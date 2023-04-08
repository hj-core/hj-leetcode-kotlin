package com.hj.leetcode.kotlin.problem133

/**
 * LeetCode page: [133. Clone Graph](https://leetcode.com/problems/clone-graph/);
 */
class Solution {
    /* Complexity:
     * Time O(V+E) and Space O(V) where V and E are the number of nodes and edges in node;
     */
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        return cloneGraph(node, hashMapOf())
    }

    private fun cloneGraph(
        node: Node,
        nodeClone: MutableMap<Node, Node> // (key, value) = (origin, clone)
    ): Node {
        if (nodeClone[node] != null) {
            return nodeClone[node]!!
        }

        val clone = nodeClone.computeIfAbsent(node) { Node(node.`val`) }
        for (neighbor in node.neighbors) {
            if (neighbor != null) {
                val neighborClone = cloneGraph(neighbor, nodeClone)
                clone.neighbors.add(neighborClone)
            }
        }
        return clone
    }
}

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}