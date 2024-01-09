package com.hj.leetcode.kotlin.problem872

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [872. Leaf-Similar Trees](https://leetcode.com/problems/leaf-similar-trees/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N and M are the number of nodes
     * in root1 and root2, respectively;
     */
    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        val sequence1 = root1.leafValueSequence().iterator()
        val sequence2 = root2.leafValueSequence().iterator()

        while (sequence1.hasNext() && sequence2.hasNext()) {
            if (sequence1.next() != sequence2.next()) {
                return false
            }
        }
        return sequence1.hasNext() == sequence2.hasNext()
    }

    private fun TreeNode?.leafValueSequence(): Sequence<Int> = when {
        this == null -> emptySequence()
        this.isLeaf() -> sequenceOf(`val`)
        else -> sequence {
            yieldAll(left.leafValueSequence())
            yieldAll(right.leafValueSequence())
        }
    }

    private fun TreeNode.isLeaf() = this.left == null && this.right == null
}