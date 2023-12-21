package com.hj.leetcode.kotlin.problem226

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        root.apply {
            invertTree(left)
            invertTree(right)
            left = right.also { right = left }
        }
        return root
    }
}