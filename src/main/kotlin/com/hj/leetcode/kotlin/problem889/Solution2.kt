package com.hj.leetcode.kotlin.problem889

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [889. Construct Binary Tree from Preorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of `preorder` and `postorder`.
    fun constructFromPrePost(
        preorder: IntArray,
        postorder: IntArray,
    ): TreeNode? {
        if (preorder.isEmpty()) {
            return null
        }
        var preIndex = 0
        var postIndex = 0
        val stack = mutableListOf<TreeNode>()

        val result = TreeNode(preorder[preIndex])
        stack.add(result)
        preIndex++

        while (preIndex < preorder.size) {
            var isLeft = true
            while (stack.last().`val` == postorder[postIndex]) {
                isLeft = false
                stack.removeLast()
                postIndex++
            }

            val node = TreeNode(preorder[preIndex])
            if (isLeft) {
                stack.last().left = node
            } else {
                stack.last().right = node
            }
            stack.add(node)
            preIndex++
        }
        return result
    }
}
