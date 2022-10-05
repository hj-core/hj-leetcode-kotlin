package com.hj.leetcode.kotlin.problem623

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [623. Add One Row to Tree](https://leetcode.com/problems/add-one-row-to-tree/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(B) where N and B are the number of nodes and max breadth on levels up to depth;
     */
    fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
        if (depth == 1) return TreeNode(`val`).also { it.left = root }

        val nodesOnOneLevelBefore = findNodesAtDepth(root, depth - 1)

        for (node in nodesOnOneLevelBefore) {
            node.insertNewNodesLeftAndRight(`val`)
        }
        return root
    }

    private fun findNodesAtDepth(root: TreeNode?, depth: Int): List<TreeNode> {
        if (root == null) return emptyList()

        var currDepth = 1
        val currNodes = ArrayDeque<TreeNode>()
        currNodes.addLast(root)

        while (currDepth < depth) {
            repeat(currNodes.size) {
                val node = currNodes.removeFirst()
                node.left?.let { currNodes.addLast(it) }
                node.right?.let { currNodes.addLast(it) }
            }
            currDepth++
        }
        return currNodes.toList()
    }

    private fun TreeNode.insertNewNodesLeftAndRight(valueOfNewNode: Int) {
        val newLeft = TreeNode(valueOfNewNode).also { newNode -> newNode.left = left }
        left = newLeft

        val newRight = TreeNode(valueOfNewNode).also { newNode -> newNode.right = right }
        right = newRight
    }
}