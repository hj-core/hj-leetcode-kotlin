package com.hj.leetcode.kotlin.problem783

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [783. Minimum Distance Between BST Nodes](https://leetcode.com/problems/minimum-distance-between-bst-nodes/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun minDiffInBST(root: TreeNode?): Int {
        requireNotNull(root) { "Constraint of the problem is not satisfied." }

        var prevValue: Int? = null
        var minDiff: Int? = null

        inOrderTraversal(root) { currValue ->
            prevValue?.let { prevValue ->
                val diff = currValue - prevValue
                minDiff = minOf(minDiff ?: diff, diff)
            }
            prevValue = currValue
        }
        return checkNotNull(minDiff)
    }

    private fun inOrderTraversal(root: TreeNode, sideEffect: (nodeValue: Int) -> Unit) {
        root.left?.let { inOrderTraversal(it, sideEffect) }
        sideEffect(root.`val`)
        root.right?.let { inOrderTraversal(it, sideEffect) }
    }
}