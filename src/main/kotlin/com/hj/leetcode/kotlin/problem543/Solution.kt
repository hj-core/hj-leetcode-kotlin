package com.hj.leetcode.kotlin.problem543

import com.hj.leetcode.kotlin.common.model.TreeNode
import kotlin.math.max

/**
 * LeetCode page: [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var result = -1
        dfsHeight(root) { result = max(result, it) }
        return result
    }

    private fun dfsHeight(
        root: TreeNode?,
        // hugLength is the length of the longest path in the subtree that pass through its root
        onEachNode: (hugLength: Int) -> Unit
    ): Int {
        if (root == null) {
            return -1
        }

        val leftHeight = dfsHeight(root.left, onEachNode)
        val rightHeight = dfsHeight(root.right, onEachNode)
        onEachNode(2 + leftHeight + rightHeight)
        return 1 + max(leftHeight, rightHeight)
    }
}