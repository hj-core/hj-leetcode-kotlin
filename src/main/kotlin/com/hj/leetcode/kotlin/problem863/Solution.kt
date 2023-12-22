package com.hj.leetcode.kotlin.problem863

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        requireNotNull(root)
        requireNotNull(target)

        val adjacencyList = adjacencyList(root)
        val bfsQueue = ArrayDeque<TreeNode>()
        val visited = hashSetOf<TreeNode>()

        var currentDistance = 0
        bfsQueue.addLast(target)
        visited.add(target)

        while (bfsQueue.isNotEmpty()) {
            if (currentDistance == k) {
                break
            }
            repeat(bfsQueue.size) {
                val node = bfsQueue.removeFirst()
                val adjacentNodes = adjacencyList[node] ?: emptyList()
                for (adjacentNode in adjacentNodes) {
                    if (adjacentNode !in visited) {
                        bfsQueue.addLast(adjacentNode)
                        visited.add(adjacentNode)
                    }
                }
            }
            currentDistance++
        }
        return bfsQueue.map { it.`val` }
    }

    private fun adjacencyList(root: TreeNode?): Map<TreeNode, List<TreeNode>> {
        val result = hashMapOf<TreeNode, MutableList<TreeNode>>()
        preorderTraversal(root) { node ->
            node.left?.let { left ->
                result.computeIfAbsent(node) { mutableListOf() }.add(left)
                result.computeIfAbsent(left) { mutableListOf() }.add(node)
            }
            node.right?.let { right ->
                result.computeIfAbsent(node) { mutableListOf() }.add(right)
                result.computeIfAbsent(right) { mutableListOf() }.add(node)
            }
        }
        return result
    }

    private fun preorderTraversal(root: TreeNode?, onEachNode: (node: TreeNode) -> Unit) {
        if (root == null) {
            return
        }
        onEachNode(root)
        preorderTraversal(root.left, onEachNode)
        preorderTraversal(root.right, onEachNode)
    }
}