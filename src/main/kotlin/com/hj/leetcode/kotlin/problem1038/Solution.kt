package com.hj.leetcode.kotlin.problem1038

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1038. Binary Search Tree to Greater Sum Tree](https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun bstToGst(root: TreeNode?): TreeNode? {
        bstToGst(root, intArrayOf(0))
        return root
    }

    private fun bstToGst(root: TreeNode?, lastSuffixSum: IntArray) {
        if (root == null) {
            return
        }
        // Reversed inorder traversal
        bstToGst(root.right, lastSuffixSum)
        root.`val` += lastSuffixSum[0]
        lastSuffixSum[0] = root.`val`
        bstToGst(root.left, lastSuffixSum)
    }
}