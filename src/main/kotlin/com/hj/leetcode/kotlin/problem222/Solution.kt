package com.hj.leetcode.kotlin.problem222

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [222. Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes/);
 */
class Solution {
    /* Complexity:
     * Time O((LogN)^2) and Space O(LogN) where N is the number of nodes in root;
     */
    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0

        val heightOfLeft = getHeightOfCompleteBinaryTree(root.left)
        val heightOfRight = getHeightOfCompleteBinaryTree(root.right)

        return when (heightOfRight) {
            heightOfLeft ->
                1 + getSizeOfPerfectBinaryTree(heightOfLeft) + countNodes(root.right)
            heightOfLeft - 1 ->
                1 + countNodes(root.left) + getSizeOfPerfectBinaryTree(heightOfRight)
            else -> throw IllegalStateException()
        }
    }

    private fun getHeightOfCompleteBinaryTree(root: TreeNode?): Int {
        var height = -1
        var node = root

        while (node != null) {
            height++
            node = node.left
        }
        return height
    }

    private fun getSizeOfPerfectBinaryTree(height: Int): Int {
        require(height >= -1)
        return if (height == -1) 0 else (2 shl height) - 1
    }
}
