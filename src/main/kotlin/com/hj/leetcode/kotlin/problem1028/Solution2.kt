package com.hj.leetcode.kotlin.problem1028

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1028. Recover a Tree From Preorder Traversal](https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(LogN) where N is the length of `traversal`.
    fun recoverFromPreorder(traversal: String): TreeNode? {
        val progress = Progress(traversal, -1, -1, 0)
        progress.advance()
        val result = TreeNode(progress.value)
        val stack = mutableListOf(result)

        while (progress.nextIndex < traversal.length) {
            progress.advance()
            val node = TreeNode(progress.value)
            while (stack.size > progress.depth) {
                stack.removeLast()
            }

            if (stack.last().left == null) {
                stack.last().left = node
            } else {
                stack.last().right = node
            }
            stack.add(node)
        }
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
