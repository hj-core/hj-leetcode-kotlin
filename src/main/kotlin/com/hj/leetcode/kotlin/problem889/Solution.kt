package com.hj.leetcode.kotlin.problem889

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [889. Construct Binary Tree from Preorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of `preorder` and `postorder`.
    fun constructFromPrePost(
        preorder: IntArray,
        postorder: IntArray,
    ): TreeNode? =
        constructSubtree(
            preorder,
            postorder,
            0,
            0,
            preorder.size,
            postIndexMap(postorder),
        )

    private fun postIndexMap(postorder: IntArray): IntArray {
        val result = IntArray(postorder.size + 1)
        result[0] = -1
        for ((index, value) in postorder.withIndex()) {
            result[value] = index
        }
        return result
    }

    private fun constructSubtree(
        preorder: IntArray,
        postorder: IntArray,
        preStart: Int,
        postStart: Int,
        size: Int,
        postIndexMap: IntArray, // postIndexMap[value]::= the index of value in postorder
    ): TreeNode? {
        if (size == 0) {
            return null
        }
        require(preorder[preStart] == postorder[postStart + size - 1])
        val result = TreeNode(preorder[preStart])
        if (size == 1) {
            return result
        }

        val leftSize = postIndexMap[preorder[preStart + 1]] - postStart + 1
        result.left =
            constructSubtree(
                preorder,
                postorder,
                preStart + 1,
                postStart,
                leftSize,
                postIndexMap,
            )
        result.right =
            constructSubtree(
                preorder,
                postorder,
                preStart + 1 + leftSize,
                postStart + leftSize,
                size - 1 - leftSize,
                postIndexMap,
            )
        return result
    }
}
