package com.hj.leetcode.kotlin.problem623

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [623. Add One Row to Tree](https://leetcode.com/problems/add-one-row-to-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(depth) where N is the number of nodes on levels up to depth;
     */
    fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
        if (depth == 1) {
            return TreeNode(`val`).apply { left = root }
        }

        dfs(root,1, `val`, depth)
        return root
    }

    private fun dfs(node: TreeNode?, depth: Int, rowValue: Int, rowDepth: Int) {
        check(depth < rowDepth)
        when {
            node == null -> return
            depth == rowDepth - 1 -> insertRow(node, rowValue)
            else -> {
                dfs(node.left, depth + 1, rowValue, rowDepth)
                dfs(node.right, depth + 1, rowValue, rowDepth)
            }
        }
    }

    private fun insertRow(node: TreeNode, rowValue: Int) {
        val newLeft = TreeNode(rowValue).apply { left = node.left }
        node.left = newLeft

        val newRight = TreeNode(rowValue).apply { right = node.right }
        node.right = newRight
    }
}