package com.hj.leetcode.kotlin.problem101

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true

        val dfsStack1 = ArrayDeque<TreeNode>()
        val dfsStack2 = ArrayDeque<TreeNode>()

        root.left?.let { dfsStack1.addLast(it) }
        root.right?.let { dfsStack2.addLast(it) }

        while (dfsStack1.isNotEmpty() && dfsStack2.isNotEmpty()) {
            val node1 = dfsStack1.removeLast()
            val node2 = dfsStack2.removeLast()

            val isAsymmetric = node1.`val` != node2.`val` ||
                    (node1.left == null) != (node2.right == null) ||
                    (node2.left == null) != (node1.right == null)
            if (isAsymmetric) return false

            node1.right?.let { dfsStack1.addLast(it) }
            node1.left?.let { dfsStack1.addLast(it) }

            node2.left?.let { dfsStack2.addLast(it) }
            node2.right?.let { dfsStack2.addLast(it) }
        }
        return dfsStack1.isEmpty() && dfsStack2.isEmpty()
    }
}