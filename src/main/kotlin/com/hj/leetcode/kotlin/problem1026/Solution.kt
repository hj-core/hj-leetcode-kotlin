package com.hj.leetcode.kotlin.problem1026

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1026. Maximum Difference Between Node and Ancestor](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun maxAncestorDiff(root: TreeNode?): Int {
        return root
            ?.let { findMaxAncestorDiff(it, it.`val`, it.`val`) }
            ?: throw IllegalArgumentException()
    }

    private fun findMaxAncestorDiff(root: TreeNode?, pathMin: Int, pathMax: Int): Int {
        if (root == null) return pathMax - pathMin
        val newPathMin = minOf(pathMin, root.`val`)
        val newPathMax = maxOf(pathMax, root.`val`)
        return maxOf(
            findMaxAncestorDiff(root.left, newPathMin, newPathMax),
            findMaxAncestorDiff(root.right, newPathMin, newPathMax)
        )
    }
}