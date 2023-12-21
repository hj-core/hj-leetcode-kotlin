package com.hj.leetcode.kotlin.problem129

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun sumNumbers(root: TreeNode?): Int {
        if (root == null) return 0
        var result = 0
        root.dfs { leafPath -> result += computeNumber(leafPath) }
        return result
    }

    private fun TreeNode.dfs(
        currPath: MutableList<TreeNode> = mutableListOf(),
        sideEffectOnLeafPath: (leafPath: List<TreeNode>) -> Unit
    ) {
        currPath.add(this)
        if (this.isLeaf()) {
            sideEffectOnLeafPath(currPath)
        } else {
            left?.dfs(currPath, sideEffectOnLeafPath)
            right?.dfs(currPath, sideEffectOnLeafPath)
        }
        currPath.apply { removeAt(lastIndex) }
    }

    private fun TreeNode.isLeaf() = left == null && right == null

    private fun computeNumber(leafPath: List<TreeNode>): Int {
        return leafPath.fold(0) { acc, treeNode -> acc * 10 + treeNode.`val` }
    }
}