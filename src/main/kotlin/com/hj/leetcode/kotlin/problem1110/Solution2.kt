package com.hj.leetcode.kotlin.problem1110

import com.hj.leetcode.kotlin.common.model.TreeNode

class Solution2 {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N is the number of nodes in root
     * and M is the size of to_delete;
     */
    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        if (root == null) {
            return emptyList()
        }

        val toDelete = to_delete.toSet()
        val result = mutableListOf<TreeNode>()
        if (root.`val` !in toDelete) {
            result.add(root)
        }

        postorderTraversal(root, null) { node, parentNode ->
            if (node.`val` in toDelete) {
                if (parentNode?.left == node) {
                    parentNode.left = null
                } else {
                    parentNode?.right = null
                }

                node.left?.let { result.add(it) }
                node.left = null
                node.right?.let { result.add(it) }
                node.right = null
            }
        }
        return result
    }

    private fun postorderTraversal(
        root: TreeNode?,
        parentNode: TreeNode?,
        onEachNode: (node: TreeNode, parentNode: TreeNode?) -> Unit,
    ) {
        if (root == null) {
            return
        }
        postorderTraversal(root.left, root, onEachNode)
        postorderTraversal(root.right, root, onEachNode)
        onEachNode(root, parentNode)
    }
}