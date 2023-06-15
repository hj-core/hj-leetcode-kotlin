package com.hj.leetcode.kotlin.problem1161

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1161. Maximum Level Sum of a Binary Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number nodes and height of root;
     */
    fun maxLevelSum(root: TreeNode?): Int {
        checkNotNull(root)
        val levelSums = levelSums(root)
        val maxLevelSum = levelSums.values.max()!!
        return levelSums
            .filter { it.value == maxLevelSum }
            .keys
            .min()!!
    }

    private fun levelSums(root: TreeNode): Map<Int, Int> {
        val result = hashMapOf<Int, Int>() // entry = (level, sum)
        root.dfs(currentLevel = 1) { level: Int, value: Int ->
            result[level] = (result[level] ?: 0) + value
        }
        return result
    }

    private fun TreeNode.dfs(currentLevel: Int, onEachNode: (level: Int, value: Int) -> Unit) {
        onEachNode(currentLevel, `val`)
        left?.dfs(currentLevel + 1, onEachNode)
        right?.dfs(currentLevel + 1, onEachNode)
    }
}