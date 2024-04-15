package com.hj.leetcode.kotlin.problem129

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun sumNumbers(root: TreeNode?): Int {
        return dfs(root, 0)
    }

    private fun dfs(node: TreeNode?, parentNumber: Int): Int {
        if (node == null) {
            return 0
        }

        val number = parentNumber * 10 + node.`val`
        return if (node.isLeaf()) {
            number
        } else {
            dfs(node.left, number)+ dfs(node.right, number)
        }
    }

    private fun TreeNode?.isLeaf(): Boolean {
        return this != null && left == null && right == null
    }
}