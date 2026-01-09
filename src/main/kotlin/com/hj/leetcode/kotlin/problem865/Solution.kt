package com.hj.leetcode.kotlin.problem865

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [865. Smallest Subtree with all the Deepest Nodes](https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(H), where N and H are the number of nodes
    // and the height of the root, respectively.
    fun subtreeWithAllDeepest(root: TreeNode?): TreeNode? =
        postOrderTraversal(root).first

    // Returns the smallest subtree with all deepest and the height of
    // the root.
    private fun postOrderTraversal(
        root: TreeNode?,
    ): Pair<TreeNode?, Int> {
        if (root == null) {
            return Pair(null, 0)
        }

        val (lSmallest, lHeight) = postOrderTraversal(root.left)
        val (rSmallest, rHeight) = postOrderTraversal(root.right)

        return when {
            lHeight > rHeight -> Pair(lSmallest, lHeight + 1)
            lHeight < rHeight -> Pair(rSmallest, rHeight + 1)
            else -> Pair(root, lHeight + 1)
        }
    }
}
