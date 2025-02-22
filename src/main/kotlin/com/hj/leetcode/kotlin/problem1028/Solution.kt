package com.hj.leetcode.kotlin.problem1028

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1028. Recover a Tree From Preorder Traversal](https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(LogN) where N is the length of `traversal`.
    fun recoverFromPreorder(traversal: String): TreeNode? {
        val progress = Progress(traversal, -1, -1, 0)
        progress.advance()
        return recover(progress, -1)
    }

    private fun recover(
        progress: Progress,
        parentDepth: Int,
    ): TreeNode? {
        if (progress.depth <= parentDepth) {
            return null
        }
        val result = TreeNode(progress.value)
        progress.advance()
        result.left = recover(progress, parentDepth + 1)
        result.right = recover(progress, parentDepth + 1)
        return result
    }

    private class Progress(
        val traversal: String,
        var value: Int,
        var depth: Int,
        var nextIndex: Int,
    ) {
        fun advance() {
            depth = 0
            while (nextIndex < traversal.length && traversal[nextIndex] == '-') {
                depth++
                nextIndex++
            }

            value = 0
            while (nextIndex < traversal.length && traversal[nextIndex] != '-') {
                val digit = traversal[nextIndex].digitToInt()
                value = value * 10 + digit
                nextIndex++
            }
        }
    }
}
