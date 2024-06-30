package com.hj.leetcode.kotlin.problem111

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun minDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        var result = 1
        val bfsQueue = ArrayDeque<TreeNode>().apply {
            addLast(root)
        }

        while (bfsQueue.isNotEmpty()) {
            repeat(bfsQueue.size) {
                val node = bfsQueue.removeFirst()
                if (node.isLeaf()) {
                    return result
                }

                node.left?.let { bfsQueue.addLast(it) }
                node.right?.let { bfsQueue.addLast(it) }
            }
            result++
        }
        throw IllegalStateException()
    }

    private fun TreeNode.isLeaf(): Boolean = left == null && right == null
}