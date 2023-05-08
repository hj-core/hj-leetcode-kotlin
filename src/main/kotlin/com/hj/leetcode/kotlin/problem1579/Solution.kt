package com.hj.leetcode.kotlin.problem1579

/**
 * LeetCode page: [1579. Remove Max Number of Edges to Keep Graph Fully Traversable](https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/);
 */
class Solution {
    /* Complexity:
     * Time O(E) and Space O(n) where E is the size of edges;
     */
    fun maxNumEdgesToRemove(n: Int, edges: Array<IntArray>): Int {
        /* Return -1 if the number of edges is less than n-1, which is the minimum number of edges
         * required for the graph to be connected.
         */
        val noEnoughEdges = edges.size < n - 1
        if (noEnoughEdges) {
            return -1
        }

        /* The idea is to construct the spanning tree for Alice and Bob respectively.
         * During the construction, UnionFind is used to determine whether the two nodes of an edge
         * are already in the same connected component.
         * If it is the case, we know the edge can be removed.
         */
        var numRemovedEdges = 0
        val alice = UnionFind(n + 1) // additional size accounts for the unused label 0
        val bob = UnionFind(n + 1) // additional size accounts for the unused label 0

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
        val aliceNumComponents = alice.numUnions - 1 // Reduce 1 for the unused label 0
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
        val bobNumComponents = bob.numUnions - 1 // Reduce 1 for the unused label 0
        if (bobNumComponents != 1) {
            return -1
        }

        // Return the number of edges that can be removed
        return numRemovedEdges
    }

    private class UnionFind(size: Int) {

        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)

        var numUnions = size
            private set

        fun union(a: Int, b: Int): Boolean {
            val aParent = find(a)
            val bParent = find(b)
            val isNoop = aParent == bParent
            if (isNoop) {
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

            numUnions--
            return true
        }

        private tailrec fun find(node: Int): Int {
            return if (parent[node] == node) node else find(parent[node])
        }
    }
}