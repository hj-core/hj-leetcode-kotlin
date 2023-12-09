package com.hj.leetcode.kotlin.problem94

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun inorderTraversal(root: TreeNode?): List<Int> {
        return buildList {
            inorderTraversal(root) { node -> add(node.`val`) }
        }
    }

    private fun inorderTraversal(
        root: TreeNode?,
        onEachNode: (node: TreeNode) -> Unit,
    ) {
        if (root == null) {
            return
        }

        inorderTraversal(root.left, onEachNode)
        onEachNode(root)
        inorderTraversal(root.right, onEachNode)
    }
}