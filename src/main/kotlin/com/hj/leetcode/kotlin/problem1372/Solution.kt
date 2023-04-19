package com.hj.leetcode.kotlin.problem1372

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1372. Longest ZigZag Path in a Binary Tree](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root
     */
    fun longestZigZag(root: TreeNode?): Int {
        var longestZigZag = 0
        dfsZigZag(root, 0, 0) { maxLengthToNode ->
            if (maxLengthToNode > longestZigZag) longestZigZag = maxLengthToNode
        }
        return longestZigZag
    }

    private fun dfsZigZag(
        root: TreeNode?,
        lengthFromLeft: Int,
        lengthFromRight: Int,
        sideEffect: (maxLengthToNode: Int) -> Unit
    ) {
        if (root == null) return
        sideEffect(maxOf(lengthFromLeft, lengthFromRight))
        dfsZigZag(root.left, 1 + lengthFromRight, 0, sideEffect)
        dfsZigZag(root.right, 0, 1 + lengthFromLeft, sideEffect)
    }
}