package com.hj.leetcode.kotlin.problem623

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [623. Add One Row to Tree](https://leetcode.com/problems/add-one-row-to-tree/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(B) where N is the number of nodes and B is the max breadth
     * on levels up to depth;
     */
    fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
        if (depth == 1) {
            return TreeNode(`val`).apply { left = root }
        }

        for (node in nodesAtDepth(root, depth - 1)) {
            insertRow(node, `val`)
        }
        return root
    }

    private fun nodesAtDepth(root: TreeNode?, depth: Int): List<TreeNode> {
        if (root == null) {
            return emptyList()
        }

        val nodes = ArrayDeque<TreeNode>()
        var currDepth = 1
        nodes.addLast(root)

        while (currDepth < depth) {
            repeat(nodes.size) {
                val node = nodes.removeFirst()
                node.left?.let { nodes.addLast(it) }
                node.right?.let { nodes.addLast(it) }
            }
            currDepth++
        }
        return nodes.toList()
    }

    private fun insertRow(node: TreeNode, rowValue: Int) {
        val newLeft = TreeNode(rowValue).apply { left = node.left }
        node.left = newLeft

        val newRight = TreeNode(rowValue).apply { right = node.right }
        node.right = newRight
    }
}