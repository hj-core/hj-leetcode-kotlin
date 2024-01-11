package com.hj.leetcode.kotlin.problem1026

import com.hj.leetcode.kotlin.common.model.TreeNode
import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1026. Maximum Difference Between Node and Ancestor](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun maxAncestorDiff(root: TreeNode?): Int {
        requireNotNull(root)
        return dfsMaxDiff(root, root.`val`, root.`val`)
    }

    private fun dfsMaxDiff(root: TreeNode?, pathMin: Int, pathMax: Int): Int {
        if (root == null) {
            return pathMax - pathMin
        }

        val newMin = min(pathMin, root.`val`)
        val newMax = max(pathMax, root.`val`)
        return max(
            dfsMaxDiff(root.left, newMin, newMax),
            dfsMaxDiff(root.right, newMin, newMax)
        )
    }
}