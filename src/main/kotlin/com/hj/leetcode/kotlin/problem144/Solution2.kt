package com.hj.leetcode.kotlin.problem144

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) & Space O(H) where N and H are the number of nodes and height of root;
     */
    fun preorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val values = mutableListOf<Int>()
        val nodeStack = ArrayDeque<TreeNode>()

        nodeStack.addLast(root)
        while (nodeStack.isNotEmpty()) {
            val node = nodeStack.removeLast()
            values.add(node.`val`)
            node.right?.let { nodeStack.addLast(it) }
            node.left?.let { nodeStack.addLast(it) }
        }
        return values
    }
}