package com.hj.leetcode.kotlin.problem1261

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1261. Find Elements in a Contaminated Binary Tree](https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/);
 */
class FindElements(
    private val root: TreeNode?,
) {
    init {
        root?.let {
            it.`val` = 0
            recover(it)
        }
    }

    // Complexity:
    // Time O(N) and Space O(H)
    // where N and H are the number of nodes and height of `root`, respectively.
    private fun recover(root: TreeNode) {
        root.left?.let {
            it.`val` = root.`val` * 2 + 1
            recover(it)
        }
        root.right?.let {
            it.`val` = root.`val` * 2 + 2
            recover(it)
        }
    }

    // Complexity:
    // Time O(Log(target)) and Space O(1).
    //
    // Note: `find` doesn't require the tree to be recovered.
    fun find(target: Int): Boolean {
        // Walk down the (virtual complete) subtree, starting from the root.
        // We decide the direction (left or right) based on the number of nodes in the level
        // containing the target, with respect to the subtree, and the position of the target
        // in that level.
        var subtree = root
        var width = (target + 1).takeHighestOneBit()
        var position = target - (width - 1)
        while (subtree != null && width > 1) {
            width = width shr 1
            if (position < width) {
                subtree = subtree.left
            } else {
                subtree = subtree.right
                position -= width
            }
        }
        return subtree != null
    }
}
