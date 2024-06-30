package com.hj.leetcode.kotlin.problem129

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun sumNumbers(root: TreeNode?): Int {
        var result = 0
        dfs(root, 0) {
            result += it
        }
        return result
    }

    private fun dfs(
        node: TreeNode?,
        parentNumber: Int,
        onEachLeaf: (number: Int) -> Unit,
    ) {
        if (node == null) {
            return
        }

        val number = parentNumber * 10 + node.`val`
        if (node.isLeaf()) {
            onEachLeaf(number)
        } else {
            dfs(node.left, number, onEachLeaf)
            dfs(node.right, number, onEachLeaf)
        }
    }

    private fun TreeNode?.isLeaf(): Boolean {
        return this != null && left == null && right == null
    }
}