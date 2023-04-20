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
        val nodeIndex = hashMapOf<TreeNode, Int>()
        val bfsQueue = ArrayDeque<TreeNode>()

        nodeIndex[root] = 1
        bfsQueue.addLast(root)
        while (bfsQueue.isNotEmpty()) {
            val levelMaxWidth =
                checkNotNull(nodeIndex[bfsQueue.last()]) - checkNotNull(nodeIndex[bfsQueue.first()]) + 1
            if (maxWidth < levelMaxWidth) maxWidth = levelMaxWidth

            repeat(bfsQueue.size) {
                val popped = bfsQueue.removeFirst()
                val indexPopped = checkNotNull(nodeIndex[popped])
                popped.left?.let {
                    nodeIndex[it] = indexPopped * 2
                    bfsQueue.addLast(it)
                }
                popped.right?.let {
                    nodeIndex[it] = indexPopped * 2 + 1
                    bfsQueue.addLast(it)
                }
            }
        }
        return maxWidth
    }
}