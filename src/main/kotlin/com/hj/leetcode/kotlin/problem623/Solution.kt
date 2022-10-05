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
        if (depth == 1) return TreeNode(`val`).also { it.left = root }

        insertRow(root, `val`, depth)
        return root
    }

    private fun insertRow(root: TreeNode?, valueOfNewNodes: Int, targetDepth: Int, currDepth: Int = 1) {
        require(currDepth < targetDepth)

        when {
            root == null -> return
            currDepth == targetDepth - 1 -> root.insertNewNodesLeftAndRight(valueOfNewNodes)
            else -> {
                insertRow(root.left, valueOfNewNodes, targetDepth, currDepth + 1)
                insertRow(root.right, valueOfNewNodes, targetDepth, currDepth + 1)
            }
        }
    }

    private fun TreeNode.insertNewNodesLeftAndRight(valueOfNewNode: Int) {
        val newLeft = TreeNode(valueOfNewNode).also { newNode -> newNode.left = left }
        left = newLeft

        val newRight = TreeNode(valueOfNewNode).also { newNode -> newNode.right = right }
        right = newRight
    }
}