package com.hj.leetcode.kotlin.problem124

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun maxPathSum(root: TreeNode?): Int {
        return root?.let {
            var maxPathSum = it.`val`
            postOrderTraversal(it) { subTreePathSum ->
                maxPathSum = maxOf(maxPathSum, subTreePathSum.maxIncludeRoot)
            }
            maxPathSum
        } ?: throw IllegalArgumentException()
    }

    private fun postOrderTraversal(
        root: TreeNode,
        sideEffect: (subTreePathSum: TreePathSum) -> Unit
    ): TreePathSum {
        return if (root.isLeaf()) {
            TreePathSum(root.`val`, root.`val`)
        } else {
            val leftPathSum = root.left?.let { postOrderTraversal(it, sideEffect) }
            val rightPathSum = root.right?.let { postOrderTraversal(it, sideEffect) }
            val maxEndAtRoot = root.`val` + maxOf(
                0,
                leftPathSum?.maxEndAtRoot ?: 0,
                rightPathSum?.maxEndAtRoot ?: 0
            )
            val maxIncludeRoot = maxOf(
                maxEndAtRoot, root.`val` + (leftPathSum?.maxEndAtRoot ?: 0) + (rightPathSum?.maxEndAtRoot ?: 0)
            )
            TreePathSum(maxEndAtRoot, maxIncludeRoot)
        }.also(sideEffect)
    }

    private data class TreePathSum(val maxEndAtRoot: Int, val maxIncludeRoot: Int)

    private fun TreeNode.isLeaf() = left == null && right == null
}