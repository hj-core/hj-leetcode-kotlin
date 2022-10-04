package com.hj.leetcode.kotlin.problem112

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [112. Path Sum](https://leetcode.com/problems/path-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        return when {
            root == null -> false
            root.isLeaf() -> targetSum == root.`val`
            else -> {
                val newTarget = targetSum - root.`val`
                hasPathSum(root.left, newTarget) || hasPathSum(root.right, newTarget)
            }
        }
    }

    private fun TreeNode.isLeaf() = this.left == null && this.right == null
}