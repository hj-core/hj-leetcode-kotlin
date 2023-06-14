package com.hj.leetcode.kotlin.problem530

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [530. Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun getMinimumDifference(root: TreeNode?): Int {
        checkNotNull(root)
        var result = initialGuessResult(root)
        var previousValue: Int? = null

        root.inorderTraversal { nodeValue ->
            previousValue?.let {
                result = minOf(result, nodeValue - it)
            }
            previousValue = nodeValue
        }
        return result
    }

    private fun initialGuessResult(root: TreeNode): Int = when {
        root.left != null -> root.`val` - checkNotNull(root.left).`val`
        root.right != null -> checkNotNull(root.right).`val` - root.`val`
        else -> throw IllegalArgumentException()
    }

    private fun TreeNode.inorderTraversal(onEachNode: (value: Int) -> Unit) {
        left?.inorderTraversal(onEachNode)
        onEachNode(`val`)
        right?.inorderTraversal(onEachNode)
    }
}