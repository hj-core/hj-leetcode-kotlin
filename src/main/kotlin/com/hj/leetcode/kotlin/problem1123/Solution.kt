package com.hj.leetcode.kotlin.problem1123

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1123. Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(H)
    // where N and H are the number of nodes and the height of the root, respectively.
    fun lcaDeepestLeaves(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        return postOrderTraversal(root, 0).first
    }

    private fun postOrderTraversal(
        root: TreeNode,
        depth: Int,
    ): Pair<TreeNode, Int> {
        if (root.left == null && root.right == null) {
            return Pair(root, depth)
        }
        if (root.left == null) {
            return postOrderTraversal(root.right!!, depth + 1)
        }
        if (root.right == null) {
            return postOrderTraversal(root.left!!, depth + 1)
        }

        val (lcaLeft, maxDepthLeft) = postOrderTraversal(root.left!!, depth + 1)
        val (lcaRight, maxDepthRight) = postOrderTraversal(root.right!!, depth + 1)

        return when {
            maxDepthLeft > maxDepthRight -> Pair(lcaLeft, maxDepthLeft)
            maxDepthLeft < maxDepthRight -> Pair(lcaRight, maxDepthRight)
            else -> Pair(root, maxDepthLeft)
        }
    }
}
