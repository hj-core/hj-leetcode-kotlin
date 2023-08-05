package com.hj.leetcode.kotlin.problem95

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [95. Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(?) and Space O(?);
     */
    fun generateTrees(n: Int): List<TreeNode?> {
        return allValidBST(1..n)
    }

    private fun allValidBST(
        values: IntRange,
        memoization: MutableMap<IntRange, List<TreeNode?>> = hashMapOf()
    ): List<TreeNode?> {
        if (values in memoization) {
            return checkNotNull(memoization[values])
        }
        if (values.isEmpty()) {
            return listOf(null)
        }

        val result = mutableListOf<TreeNode?>()
        for (rootValue in values) {
            val allValidLeftTrees = allValidBST(values.first until rootValue, memoization)
            val allValidRightTrees = allValidBST((rootValue + 1)..values.last, memoization)

            for (left in allValidLeftTrees) {
                for (right in allValidRightTrees) {
                    val root = TreeNode(rootValue).apply {
                        this.left = left.clone()
                        this.right = right.clone()
                    }
                    result.add(root)
                }
            }
        }
        memoization[values] = result
        return result
    }

    private fun TreeNode?.clone(): TreeNode? {
        if (this == null) {
            return null
        }
        return TreeNode(`val`).apply {
            left = this@clone.left.clone()
            right = this@clone.right.clone()
        }
    }
}