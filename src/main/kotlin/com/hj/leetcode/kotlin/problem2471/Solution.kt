package com.hj.leetcode.kotlin.problem2471

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2471. Minimum Number of Operations to Sort a Binary Tree by Level](https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the number of nodes in root.
     */
    fun minimumOperations(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val levelNodes = ArrayDeque<TreeNode>().apply { add(root) }
        var result = 0
        while (levelNodes.isNotEmpty()) {
            result += minOperations(levelNodes)
            toNextLevel(levelNodes)
        }
        return result
    }

    private fun minOperations(levelNodes: List<TreeNode>): Int {
        // REQUIRE!(all values are unique)
        // val oldOrder = levelNodes.indices
        val newOrder =
            MutableList(levelNodes.size) { it }.apply {
                sortBy { levelNodes[it].`val` }
            }
        var result = 0
        var i = 0

        while (i < newOrder.size) {
            val old = i
            val new = newOrder[i]
            if (new == old) {
                i++
            } else {
                newOrder[new] = new.also { newOrder[i] = newOrder[new] }
                result++
            }
        }
        return result
    }

    private fun toNextLevel(levelNodes: ArrayDeque<TreeNode>) {
        repeat(levelNodes.size) {
            val node = levelNodes.removeFirst()
            node.left?.let { levelNodes.addLast(it) }
            node.right?.let { levelNodes.addLast(it) }
        }
    }
}
