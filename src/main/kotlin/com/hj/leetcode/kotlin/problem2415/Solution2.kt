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
        if (root == null) {
            return null
        }
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
        if (level % 2 == 1) {
            node.`val` = symmetricNode.`val`.also { symmetricNode.`val` = node.`val` }
        }
        dfs(node.left, symmetricNode.right, level + 1)
        dfs(node.right, symmetricNode.left, level + 1)
    }
}
