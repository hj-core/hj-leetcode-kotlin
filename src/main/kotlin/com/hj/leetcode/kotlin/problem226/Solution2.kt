package com.hj.leetcode.kotlin.problem226

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val nodeStack = ArrayDeque<TreeNode>().also {
            it.addLast(root)
        }

        while (nodeStack.isNotEmpty()) {
            val node = nodeStack.removeLast()
            with(node) {
                left = right.also { right = left }
                left?.let { nodeStack.addLast(it) }
                right?.let { nodeStack.addLast(it) }
            }
        }
        return root
    }
}