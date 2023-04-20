package com.hj.leetcode.kotlin.problem662

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [662. Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun widthOfBinaryTree(root: TreeNode?): Int {
        if (root == null) return 0

        var maxWidth = 0
        // nodeIndex[node] ::= index of node in level order
        val nodeIndex = hashMapOf<TreeNode, Int>()
        val bfsQueue = ArrayDeque<TreeNode>()

        nodeIndex[root] = 0
        bfsQueue.addLast(root)
        while (bfsQueue.isNotEmpty()) {
            val levelMaxWidth = levelMaxWidth(bfsQueue, nodeIndex)
            if (maxWidth < levelMaxWidth) {
                maxWidth = levelMaxWidth
            }
            updateNextLevel(bfsQueue, nodeIndex)
        }
        return maxWidth
    }

    private fun levelMaxWidth(
        bfsQueue: ArrayDeque<TreeNode>,
        nodeIndex: Map<TreeNode, Int>
    ): Int {
        val leftmostIndex = checkNotNull(nodeIndex[bfsQueue.first()])
        val rightmostIndex = checkNotNull(nodeIndex[bfsQueue.last()])
        return rightmostIndex - leftmostIndex + 1
    }

    private fun updateNextLevel(
        bfsQueue: ArrayDeque<TreeNode>,
        nodeIndex: MutableMap<TreeNode, Int>
    ) {
        repeat(bfsQueue.size) {
            val node = bfsQueue.removeFirst()
            val index = checkNotNull(nodeIndex[node])
            node.left?.let {
                nodeIndex[it] = index * 2 + 1
                bfsQueue.addLast(it)
            }
            node.right?.let {
                nodeIndex[it] = index * 2 + 2
                bfsQueue.addLast(it)
            }
        }
    }
}