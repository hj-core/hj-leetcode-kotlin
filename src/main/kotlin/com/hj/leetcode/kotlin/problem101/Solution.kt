package com.hj.leetcode.kotlin.problem101

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        return isSymmetric(root?.left, root?.right)
    }

    private fun isSymmetric(tree1: TreeNode?, tree2: TreeNode?): Boolean {
        return when {
            tree1 == null || tree2 == null -> tree1 == tree2
            tree1.`val` != tree2.`val` -> false
            else -> isSymmetric(tree1.left, tree2.right) && isSymmetric(tree2.left, tree1.right)
        }
    }
}