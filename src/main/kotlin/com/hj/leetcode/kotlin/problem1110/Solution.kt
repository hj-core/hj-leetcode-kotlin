package com.hj.leetcode.kotlin.problem1110

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1110. Delete Nodes And Return Forest](https://leetcode.com/problems/delete-nodes-and-return-forest/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N) where N is the number of nodes in root
     * and M is the size of to_delete;
     */
    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        if (root == null) {
            return emptyList()
        }

        val nodes = allNodes(root) // node value to the node object
        val parentNodes = parentNodes(root) // node to its parent node
        for (value in to_delete) {
            val node = nodes[value] ?: continue
            nodes.remove(value)

            parentNodes[node]?.let {
                if (it.left == node) {
                    it.left = null
                } else {
                    it.right = null
                }
            }
            parentNodes.remove(node)
            node.left?.let { parentNodes[it] = null }
            node.right?.let { parentNodes[it] = null }
        }
        return parentNodes.keys.filter { parentNodes[it] == null }
    }

    private fun allNodes(root: TreeNode): MutableMap<Int, TreeNode> {
        val result = mutableMapOf<Int, TreeNode>()
        dfs(root) { node -> result[node.`val`] = node }
        return result
    }

    private fun dfs(root: TreeNode?, onEachNode: (node: TreeNode) -> Unit) {
        if (root == null) {
            return
        }
        onEachNode(root)
        dfs(root.left, onEachNode)
        dfs(root.right, onEachNode)
    }

    private fun parentNodes(root: TreeNode): MutableMap<TreeNode, TreeNode?> {
        val result = mutableMapOf<TreeNode, TreeNode?>()
        result[root] = null
        dfs(root) { node ->
            node.left?.let { result[it] = node }
            node.right?.let { result[it] = node }
        }
        return result
    }
}