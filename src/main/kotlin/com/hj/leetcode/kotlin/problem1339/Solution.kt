package com.hj.leetcode.kotlin.problem1339

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1339. Maximum Product of Splitted Binary Tree](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun maxProduct(root: TreeNode?): Int {
        val sum = root.sum {}
        var maxProduct = Long.MIN_VALUE
        val updateMaxProduct = { subTreeSum: Long ->
            maxProduct = maxOf(maxProduct, subTreeSum * (sum - subTreeSum))
        }
        root?.left.sum(updateMaxProduct)
        root?.right.sum(updateMaxProduct)
        return toOutput(maxProduct)
    }

    private fun TreeNode?.sum(sideEffect: (subTreeSum: Long) -> Unit): Long {
        if (this == null) return 0L
        val sum = `val` + left.sum(sideEffect) + right.sum(sideEffect)
        sideEffect(sum)
        return sum
    }

    private fun toOutput(maxProduct: Long): Int {
        val modulo = 1_000_000_007
        return (maxProduct % modulo).toInt()
    }
}