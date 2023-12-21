package com.hj.leetcode.kotlin.problem133

/**
 * LeetCode page: [133. Clone Graph](https://leetcode.com/problems/clone-graph/);
 */
class Solution2 {
    /* Complexity:
     * Time O(V+E) and Space O(V) where V and E are the number of nodes and edges in node;
     */
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null

        val nodeClone = hashMapOf<Node, Node>() // (key, value) = (origin, clone)
        val dfsStack = ArrayDeque<Node>()

        dfsStack.addLast(node)
        nodeClone[node] = Node(node.`val`)
        while (dfsStack.isNotEmpty()) {
            val origin = dfsStack.removeLast()
            val clone = checkNotNull(nodeClone[origin])
            for (neighbor in origin.neighbors) {
                if (neighbor == null) continue

                val hasNotVisited = neighbor !in nodeClone
                if (hasNotVisited) {
                    dfsStack.addLast(neighbor)
                    nodeClone[neighbor] = Node(neighbor.`val`)
                }
                val neighborClone = checkNotNull(nodeClone[neighbor])
                clone.neighbors.add(neighborClone)
            }
        }
        return nodeClone[node]
    }
}