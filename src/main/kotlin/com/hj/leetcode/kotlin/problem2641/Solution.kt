package com.hj.leetcode.kotlin.problem2641

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2641. Cousins in Binary Tree II](https://leetcode.com/problems/cousins-in-binary-tree-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root.
     */
    fun replaceValueInTree(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        val levelSums = computeLevelSums(root)
        root.`val` = 0
        dfs(root, 0) { node, depth ->
            val childrenSum = (node.left?.`val` ?: 0) + (node.right?.`val` ?: 0)
            node.left?.let { it.`val` = levelSums[depth + 1] - childrenSum }
            node.right?.let { it.`val` = levelSums[depth + 1] - childrenSum }
        }
        return root
    }

    private fun computeLevelSums(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        dfs(root, 0) { node, depth ->
            if (depth == result.size) {
                result.add(0)
            }
            result[depth] += node.`val`
        }
        return result
    }

    private fun dfs(
        root: TreeNode?,
        depth: Int,
        onEachNode: (node: TreeNode, depth: Int) -> Unit,
    ) {
        if (root == null) {
            return
        }
        onEachNode(root, depth)
        dfs(root.left, depth + 1, onEachNode)
        dfs(root.right, depth + 1, onEachNode)
    }
}
