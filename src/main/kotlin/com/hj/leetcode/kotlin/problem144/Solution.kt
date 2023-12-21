package com.hj.leetcode.kotlin.problem144

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val values = mutableListOf<Int>()
        preorderTraversal(root) { nodeValue -> values.add(nodeValue) }
        return values
    }

    private fun preorderTraversal(root: TreeNode?, sideEffect: (nodeValue: Int) -> Unit) {
        if (root == null) return
        sideEffect(root.`val`)
        preorderTraversal(root.left, sideEffect)
        preorderTraversal(root.right, sideEffect)
    }
}