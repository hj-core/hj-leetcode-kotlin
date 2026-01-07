package com.hj.leetcode.kotlin.problem1161

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1161. Maximum Level Sum of a Binary Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the number nodes in root.
    fun maxLevelSum(root: TreeNode?): Int =
        mutableListOf<Int>().let {
            dfs(checkNotNull(root), 0, it)
            it.indexOf(it.max()) + 1
        }

    private fun dfs(
        root: TreeNode,
        level: Int, // 0-indexed
        levelSum: MutableList<Int>,
    ) {
        if (levelSum.size == level) {
            levelSum.add(root.`val`)
        } else {
            levelSum[level] += root.`val`
        }

        root.left?.let { dfs(it, level + 1, levelSum) }
        root.right?.let { dfs(it, level + 1, levelSum) }
    }
}
