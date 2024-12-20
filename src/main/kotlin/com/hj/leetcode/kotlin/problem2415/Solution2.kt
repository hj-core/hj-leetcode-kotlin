package com.hj.leetcode.kotlin.problem2415

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2415. Reverse Odd Levels of Binary Tree](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(Log(N)) where N is the number of nodes in root.
     */
    fun reverseOddLevels(root: TreeNode?): TreeNode? {
        // Caution:
        // The problem asks to reverse the node values, not the nodes themselves.
        // This solution demonstrates the capability but doesn't fit the requirement.
        if (root == null) {
            return null
        }
        root.left = root.right.also { root.right = root.left }
        dfs(root.left, root.right, 1)
        return root
    }

    private fun dfs(
        node: TreeNode?,
        symmetricNode: TreeNode?,
        level: Int,
    ) {
        if (node == null || symmetricNode == null) {
            return
        }
        if (level % 2 == 0) {
            node.left = symmetricNode.right.also { symmetricNode.right = node.left }
            node.right = symmetricNode.left.also { symmetricNode.left = node.right }
        } else {
            node.left = symmetricNode.left.also { symmetricNode.left = node.left }
            node.right = symmetricNode.right.also { symmetricNode.right = node.right }
        }
        dfs(node.left, symmetricNode.right, level + 1)
        dfs(node.right, symmetricNode.left, level + 1)
    }
}
