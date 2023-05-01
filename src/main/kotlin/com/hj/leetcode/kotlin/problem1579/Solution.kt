package com.hj.leetcode.kotlin.problem1579

/**
 * LeetCode page: [1579. Remove Max Number of Edges to Keep Graph Fully Traversable](https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/);
 */
class Solution {
    /* Complexity:
     * Time O(E) and Space O(n) where E is the size of edges;
     */
    fun maxNumEdgesToRemove(n: Int, edges: Array<IntArray>): Int {
        /* Check whether there are at least n-1 edges, otherwise the graph can not be connected and
         * should return -1
         */
        val noEnoughEdges = edges.size < n - 1
        if (noEnoughEdges) {
            return -1
        }

        /* UnionFind helps determine whether two nodes in a graph are in the same connected component.
         * Create two UnionFind, one for Alice and another for Bob
         */
        val alice = UnionFind(n + 1) // additional size accounts for the unused label 0
        val bob = UnionFind(n + 1) // additional size accounts for the unused label 0

        // Use a variable to count the number of edges that can be removed
        var numRemovedEdges = 0

        // First, iterate all the type 3 edges, which can be traversed by both Alice and Bob
        for ((type, u, v) in edges) {
            if (type != 3) {
                continue
            }
            // Try to union the two nodes of the edge for both Alice and Bob
            val isRequiredEdge = alice.union(u, v)
            bob.union(u, v)
            /* If the union is no-op, the two nodes have already been connected, so the edge can be removed,
             * and we increase the number of removed edge by 1
             */
            if (!isRequiredEdge) {
                numRemovedEdges++
            }
        }

        // Iterate all the type 1 edges, which can be traversed by Alice only
        for ((type, u, v) in edges) {
            if (type != 1) {
                continue
            }
            // Try to union the two nodes of the edge for Alice
            val isRequiredEdge = alice.union(u, v)
            /* If the union is no-op, the two nodes have already been connected, so the edge can be removed,
             * and we increase the number of removed edge by 1
             */
            if (!isRequiredEdge) {
                numRemovedEdges++
            }
        }
        /* Check the number of connected components in Alice. If it is not one, then Alice cannot fully
         * traverse the graph and should return -1
         */
        val aliceNumComponents = alice.numComponents - 1 // Reduce 1 for the unused label 0
        if (aliceNumComponents != 1) {
            return -1
        }

        // Iterate all the type 2 edges, which can be traversed by Bob only
        for ((type, u, v) in edges) {
            if (type != 2) {
                continue
            }
            // Try to union the two nodes of edge for Bob
            val isRequiredEdge = bob.union(u, v)
            /* If the union is no-op, the two nodes have already been connected, so the edge can be removed,
             * and we increase the number of removed edge by 1
             */
            if (!isRequiredEdge) {
                numRemovedEdges++
            }
        }
        /* Check the number of connected components in Bob. If it is not one, then Bob cannot fully
         * traverse the graph and should return -1
         */
        val bobNumComponents = bob.numComponents - 1 // Reduce 1 for the unused label 0
        if (bobNumComponents != 1) {
            return -1
        }

        // Return the number of edges that can be removed
        return numRemovedEdges
    }

    private class UnionFind(size: Int) {

        private val parent = IntArray(size) { node -> node }
        private val rank = IntArray(size)

        var numComponents = size
            private set

        fun union(aNode: Int, bNode: Int): Boolean {
            val aParent = find(aNode)
            val bParent = find(bNode)
            val isSameParent = aParent == bParent
            if (isSameParent) {
                return false
            }

            val aParentRank = rank[aParent]
            val bParentRank = rank[bParent]
            when {
                aParentRank < bParentRank -> parent[aParent] = bParent
                aParentRank > bParentRank -> parent[bParent] = aParent
                else -> {
                    parent[bParent] = aParent
                    rank[aParent]++
                }
            }

            numComponents--
            return true
        }

        private tailrec fun find(node: Int): Int {
            return if (parent[node] == node) node else find(parent[node])
        }
    }
}