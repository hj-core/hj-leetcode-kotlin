package com.hj.leetcode.kotlin.problem1382

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1382. Balance a Binary Search Tree](https://leetcode.com/problems/balance-a-binary-search-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun balanceBST(root: TreeNode?): TreeNode? {
        val values = buildList {
            inorderTraversal(root) { node -> add(node.`val`) }
        }
        return buildBalanceBst(values, 0, values.size)
    }

    private fun inorderTraversal(root: TreeNode?, onEachNode: (node: TreeNode) -> Unit) {
        if (root == null) {
            return
        }
        inorderTraversal(root.left, onEachNode)
        onEachNode(root)
        inorderTraversal(root.right, onEachNode)
    }

    private fun buildBalanceBst(sortedValues: List<Int>, from: Int, toExclusive: Int): TreeNode? {
        if (from == toExclusive) {
            return null
        }
        val mid = (from + toExclusive) ushr 1
        val result = TreeNode(sortedValues[mid]).apply {
            left = buildBalanceBst(sortedValues, from, mid)
            right = buildBalanceBst(sortedValues, mid + 1, toExclusive)
        }
        return result
    }
}