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
        dfs(root) { maxLengthToNode ->
            if (longestZigZag < maxLengthToNode) longestZigZag = maxLengthToNode
        }
        return longestZigZag
    }

    /**
     * Perform a DFS and calls [onEachNode] passing the length of longest zigzag path to the node as an argument.
     * [zigzagLengthLeft] and [zigzagLengthRight] are the length of longest zigzag paths incident to the node through
     * a left edge and right edge respectively.
     */
    private fun dfs(
        root: TreeNode?,
        zigzagLengthLeft: Int = 0,
        zigzagLengthRight: Int = 0,
        onEachNode: (maxLengthToNode: Int) -> Unit
    ) {
        if (root == null) return
        onEachNode(maxOf(zigzagLengthLeft, zigzagLengthRight))
        dfs(root.left, 1 + zigzagLengthRight, 0, onEachNode)
        dfs(root.right, 0, 1 + zigzagLengthLeft, onEachNode)
    }
}