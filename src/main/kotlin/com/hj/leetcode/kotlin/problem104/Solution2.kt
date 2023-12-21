package com.hj.leetcode.kotlin.problem104

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0

        var maxDepth = 0
        val nodeQueue = ArrayDeque<TreeNode>().also {
            it.addLast(root)
        }

        while (nodeQueue.isNotEmpty()) {
            maxDepth++
            repeat(nodeQueue.size) {
                val node = nodeQueue.removeFirst()
                node.left?.let { nodeQueue.addLast(it) }
                node.right?.let { nodeQueue.addLast(it) }
            }
        }
        return maxDepth
    }
}