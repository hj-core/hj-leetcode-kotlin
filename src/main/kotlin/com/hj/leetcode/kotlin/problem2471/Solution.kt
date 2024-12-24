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
        // val oldOrders = levelNodes.indices
        val newOrders =
            MutableList(levelNodes.size) { it }.apply {
                sortBy { levelNodes[it].`val` }
            }
        var result = 0
        var old = 0

        while (old < newOrders.size) {
            val new = newOrders[old]
            if (new == old) {
                old++
            } else {
                newOrders[old] = newOrders[new].also { newOrders[new] = newOrders[old] }
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
