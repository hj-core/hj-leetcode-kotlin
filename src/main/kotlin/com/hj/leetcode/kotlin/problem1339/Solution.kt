package com.hj.leetcode.kotlin.problem1339

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1339. Maximum Product of Splitted Binary Tree](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the number of nodes in root.
    fun maxProduct(root: TreeNode?): Int {
        checkNotNull(root)
        val treeSum = sumOfNodes(root)

        var maxProduct = 0L
        dfs(root) { subtreeSum ->
            maxProduct =
                maxOf(
                    maxProduct,
                    subtreeSum.toLong() * (treeSum - subtreeSum),
                )
        }

        return (maxProduct % (1e9 + 7)).toInt()
    }

    private fun sumOfNodes(root: TreeNode): Int =
        root.`val` +
            (root.left?.let { sumOfNodes(it) } ?: 0) +
            (root.right?.let { sumOfNodes(it) } ?: 0)

    private fun dfs(
        root: TreeNode,
        onEachSubtreeSum: (Int) -> Unit,
    ): Int {
        val leftSum =
            root.left?.let { dfs(it, onEachSubtreeSum) } ?: 0

        val rightSum =
            root.right?.let { dfs(it, onEachSubtreeSum) } ?: 0

        val treeSum = leftSum + rightSum + root.`val`
        onEachSubtreeSum(treeSum)
        return treeSum
    }
}
