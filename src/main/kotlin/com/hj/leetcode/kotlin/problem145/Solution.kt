package com.hj.leetcode.kotlin.problem145

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/);
 */
class Solution {
    /* Complexity:
     * Timme O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun postorderTraversal(root: TreeNode?): List<Int> =
        buildList {
            postorder(root) { node -> add(node.`val`) }
        }

    private fun postorder(
        root: TreeNode?,
        onEachNode: (node: TreeNode) -> Unit,
    ) {
        if (root == null) {
            return
        }
        postorder(root.left, onEachNode)
        postorder(root.right, onEachNode)
        onEachNode(root)
    }
}
