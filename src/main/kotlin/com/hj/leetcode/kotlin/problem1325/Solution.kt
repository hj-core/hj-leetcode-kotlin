package com.hj.leetcode.kotlin.problem1325

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1325. Delete Leaves With a Given Value](https://leetcode.com/problems/delete-leaves-with-a-given-value/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun removeLeafNodes(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) {
            return null
        }

        root.left = removeLeafNodes(root.left, target)
        root.right = removeLeafNodes(root.right, target)
        return if (root.isLeaf() && root.`val` == target) null else root
    }

    private fun TreeNode.isLeaf(): Boolean = left == null && right == null
}