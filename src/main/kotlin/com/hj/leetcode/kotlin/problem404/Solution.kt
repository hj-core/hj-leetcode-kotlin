package com.hj.leetcode.kotlin.problem404

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [404. Sum of Left Leaves](https://leetcode.com/problems/sum-of-left-leaves/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        return dfs(root, false)
    }

    private fun dfs(node: TreeNode?, isLeft: Boolean): Int = when {
        node == null -> 0
        node.isLeaf() -> if (isLeft) node.`val` else 0
        else -> dfs(node.left, true) + dfs(node.right, false)
    }

    private fun TreeNode.isLeaf(): Boolean {
        return left == null && right == null
    }
}