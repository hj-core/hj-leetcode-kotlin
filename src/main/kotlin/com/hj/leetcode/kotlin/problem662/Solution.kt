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

        var maxWidth = 0L
        /* In order to avoid number overflow(for example, in the case of a vertical zigzag tree), the indices
         * of nodes are computed as following: During a BFS, when computing the indices of nodes in next level,
         * the indices of nodes in current level are normalized such that the leftmost node in current level
         * has an index of 0.
         */
        val nodeIndex = hashMapOf<TreeNode, Long>()
        val bfsQueue = ArrayDeque<TreeNode>()

        nodeIndex[root] = 0L
        bfsQueue.addLast(root)
        while (bfsQueue.isNotEmpty()) {
            val levelMaxWidth = levelMaxWidth(bfsQueue, nodeIndex)
            if (maxWidth < levelMaxWidth) {
                maxWidth = levelMaxWidth
            }
            updateNextLevel(bfsQueue, nodeIndex)
        }
        return maxWidth.toInt()
    }

    private fun levelMaxWidth(
        bfsQueue: ArrayDeque<TreeNode>,
        nodeIndex: Map<TreeNode, Long>
    ): Long {
        val leftmostIndex = checkNotNull(nodeIndex[bfsQueue.first()])
        val rightmostIndex = checkNotNull(nodeIndex[bfsQueue.last()])
        return rightmostIndex - leftmostIndex + 1
    }

    private fun updateNextLevel(
        bfsQueue: ArrayDeque<TreeNode>,
        nodeIndex: MutableMap<TreeNode, Long>
    ) {
        val indexShift = checkNotNull(nodeIndex[bfsQueue.first()]) * (-1)

        repeat(bfsQueue.size) {
            val node = bfsQueue.removeFirst()
            // Normalize the index to avoid number overflow of indices in next level
            val normalizedIndex = checkNotNull(nodeIndex[node]) + indexShift

            node.left?.let {
                nodeIndex[it] = normalizedIndex * 2
                bfsQueue.addLast(it)
            }
            node.right?.let {
                nodeIndex[it] = normalizedIndex * 2 + 1
                bfsQueue.addLast(it)
            }
        }
    }
}