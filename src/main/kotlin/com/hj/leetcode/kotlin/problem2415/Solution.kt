package com.hj.leetcode.kotlin.problem2415

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2415. Reverse Odd Levels of Binary Tree](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root.
     */
    fun reverseOddLevels(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        // Caution: The problem asks to reverse the node values, not the nodes themselves
        var level = 0
        var levelNodes = listOf(root)

        while (levelNodes.isNotEmpty()) {
            if (level % 2 == 1) {
                reverseNodeValues(levelNodes)
            }
            levelNodes = nextLevelNodes(levelNodes)
            level++
        }
        return root
    }

    private fun reverseNodeValues(levelNodes: List<TreeNode>) {
        var left = 0
        var right = levelNodes.lastIndex
        while (left < right) {
            val temp = levelNodes[left].`val`
            levelNodes[left].`val` = levelNodes[right].`val`
            levelNodes[right].`val` = temp
            left++
            right--
        }
    }

    private fun nextLevelNodes(levelNodes: List<TreeNode>): List<TreeNode> =
        buildList {
            if (levelNodes[0].left != null) {
                for (node in levelNodes) {
                    add(checkNotNull(node.left))
                    add(checkNotNull(node.right))
                }
            }
        }
}
