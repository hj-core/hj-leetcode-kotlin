package com.hj.leetcode.kotlin.problem104

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val leftMaxDepth = maxDepth(root.left)
        val rightMaxDepth = maxDepth(root.right)
        // WARNING: Could throw StackOverflowError
        return 1 + maxOf(leftMaxDepth, rightMaxDepth)
    }
}