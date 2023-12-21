package com.hj.leetcode.kotlin.problem129

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun sumNumbers(root: TreeNode?): Int {
        if (root == null) return 0
        var result = 0
        root.dfs { leafPathSum -> result += leafPathSum }
        return result
    }

    private fun TreeNode.dfs(
        currPathSum: Int = 0,
        sideEffectOnLeafPathSum: (leafPathSum: Int) -> Unit
    ) {
        val newPathSum = currPathSum * 10 + `val`
        if (this.isLeaf()) {
            sideEffectOnLeafPathSum(newPathSum)
        } else {
            left?.dfs(newPathSum, sideEffectOnLeafPathSum)
            right?.dfs(newPathSum, sideEffectOnLeafPathSum)
        }
    }

    private fun TreeNode.isLeaf() = left == null && right == null
}