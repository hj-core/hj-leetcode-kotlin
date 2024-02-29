package com.hj.leetcode.kotlin.problem1609

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1609. Even Odd Tree](https://leetcode.com/problems/even-odd-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun isEvenOddTree(root: TreeNode?): Boolean {
        val recentValues = mutableListOf<Int>() // the last visited values in each level
        var hasViolations = false

        preorderTraversal(root, 0) { value, depth ->
            when {
                hasViolations -> return@preorderTraversal
                depth and 1 == value and 1 -> hasViolations = true
                depth == recentValues.size -> recentValues.add(value)
                depth and 1 == 0 && value <= recentValues[depth] -> hasViolations = true
                depth and 1 == 1 && recentValues[depth] <= value -> hasViolations = true
                else -> recentValues[depth] = value
            }
        }
        return !hasViolations
    }

    private fun preorderTraversal(
        node: TreeNode?,
        depth: Int,
        onEachNode: (nodeValue: Int, depth: Int) -> Unit,
    ) {
        if (node == null) {
            return
        }
        onEachNode(node.`val`, depth)
        preorderTraversal(node.left, depth + 1, onEachNode)
        preorderTraversal(node.right, depth + 1, onEachNode)
    }
}