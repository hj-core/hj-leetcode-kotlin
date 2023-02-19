package com.hj.leetcode.kotlin.problem103

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val sortedValues = mutableListOf<List<Int>>()
        var sortedCurrentLevel = listOf(root)

        while (sortedCurrentLevel.isNotEmpty()) {
            sortedValues.add(sortedCurrentLevel.map { it.`val` })
            val isNextLevelZig = sortedValues.size and 1 == 0
            sortedCurrentLevel = sortedNextLevel(sortedCurrentLevel, isNextLevelZig)
        }

        return sortedValues
    }

    private fun sortedNextLevel(sortedCurrentLevel: List<TreeNode>, isNextLevelZig: Boolean): List<TreeNode> {
        return if (isNextLevelZig) {
            buildNextLevelInZigOrder(sortedCurrentLevel)
        } else {
            buildNextLevelInZagOrder(sortedCurrentLevel)
        }
    }

    private fun buildNextLevelInZigOrder(sortedCurrentLevel: List<TreeNode>): List<TreeNode> {
        val sortedNextLevel = mutableListOf<TreeNode>()
        for (index in sortedCurrentLevel.indices.reversed()) {
            val parentNode = sortedCurrentLevel[index]
            parentNode.left?.let { sortedNextLevel.add(it) }
            parentNode.right?.let { sortedNextLevel.add(it) }
        }
        return sortedNextLevel
    }

    private fun buildNextLevelInZagOrder(sortedCurrentLevel: List<TreeNode>): List<TreeNode> {
        val sortedNextLevel = mutableListOf<TreeNode>()
        for (index in sortedCurrentLevel.indices.reversed()) {
            val parentNode = sortedCurrentLevel[index]
            parentNode.right?.let { sortedNextLevel.add(it) }
            parentNode.left?.let { sortedNextLevel.add(it) }
        }
        return sortedNextLevel
    }
}