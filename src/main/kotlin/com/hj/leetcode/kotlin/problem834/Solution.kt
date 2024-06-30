package com.hj.leetcode.kotlin.problem834

/**
 * LeetCode page: [834. Sum of Distances in Tree](https://leetcode.com/problems/sum-of-distances-in-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun sumOfDistancesInTree(n: Int, edges: Array<IntArray>): IntArray {
        val root = 0
        val adjacencyList = adjacencyList(edges)
        val subTreeSizes = subTreeSizes(root, n, adjacencyList)
        val result = IntArray(n)
        result[root] = findRootSumOfDistances(root, 0, root, adjacencyList)

        for (childNode in adjacencyList[root] ?: emptySet()) {
            findOtherNodesSumOfDistances(
                childNode,
                root,
                n, adjacencyList, subTreeSizes,
                result
            )
        }
        return result
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, Set<Int>> {
        val result = mutableMapOf<Int, MutableSet<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { mutableSetOf() }.add(v)
            result.computeIfAbsent(v) { mutableSetOf() }.add(u)
        }
        return result
    }

    private fun subTreeSizes(
        root: Int,
        totalNodes: Int,
        adjacencyList: Map<Int, Set<Int>>,
    ): IntArray {
        val result = IntArray(totalNodes)
        findSubTreeSizes(root, root, adjacencyList) { node, size ->
            result[node] = size
        }
        return result
    }

    private fun findSubTreeSizes(
        root: Int,
        parentNode: Int,
        adjacencyList: Map<Int, Set<Int>>,
        onEachNode: (node: Int, subTreeSize: Int) -> Unit,
    ): Int {
        var result = 1
        for (childNode in adjacencyList[root] ?: emptySet()) {
            if (childNode == parentNode) {
                continue
            }
            result += findSubTreeSizes(childNode, root, adjacencyList, onEachNode)
        }
        onEachNode(root, result)
        return result
    }

    private fun findRootSumOfDistances(
        root: Int,
        depth: Int,
        parentNode: Int,
        adjacencyList: Map<Int, Set<Int>>,
    ): Int {
        var result = depth
        for (childNode in adjacencyList[root] ?: emptySet()) {
            if (childNode == parentNode) {
                continue
            }
            result += findRootSumOfDistances(childNode, depth + 1, root, adjacencyList)
        }
        return result
    }

    private fun findOtherNodesSumOfDistances(
        node: Int,
        parentNode: Int,
        totalNode: Int,
        adjacencyList: Map<Int, Set<Int>>,
        subTreeSizes: IntArray,
        memoization: IntArray,
    ) {
        memoization[node] = (memoization[parentNode]
                + (totalNode - subTreeSizes[node])
                - subTreeSizes[node])

        for (childNode in adjacencyList[node] ?: emptySet()) {
            if (childNode == parentNode) {
                continue
            }
            findOtherNodesSumOfDistances(
                childNode,
                node,
                totalNode, adjacencyList, subTreeSizes,
                memoization
            )
        }
    }
}