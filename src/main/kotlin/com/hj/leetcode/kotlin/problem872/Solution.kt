package com.hj.leetcode.kotlin.problem872

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [872. Leaf-Similar Trees](https://leetcode.com/problems/leaf-similar-trees/description/);
 *
 * TODO : There is solution that compares the leaves one by one, thus reduce the space complexity;
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(M+N) where M and N are the number of nodes of root1 and root2;
     */
    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        return root1.findAllLeafValues() == root2.findAllLeafValues()
    }

    private fun TreeNode?.findAllLeafValues(): List<Int> {
        val leafValues = mutableListOf<Int>()
        traversalLeaves { leaf -> leafValues.add(leaf.`val`) }
        return leafValues
    }

    private fun TreeNode?.traversalLeaves(sideEffect: (leaf: TreeNode) -> Unit) {
        when {
            this == null -> return
            this.isLeaf() -> sideEffect(this)
            else -> {
                left.traversalLeaves(sideEffect)
                right.traversalLeaves(sideEffect)
            }
        }
    }

    private fun TreeNode.isLeaf() = left == null && right == null
}