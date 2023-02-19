package com.hj.leetcode.kotlin.problem103

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val sortedValues = mutableListOf<List<Int>>()
        val currLevelNodes = ArrayDeque<TreeNode>().also { it.addLast(root) }

        while (currLevelNodes.isNotEmpty()) {
            val isZigLevel = sortedValues.size and 1 == 0

            if (isZigLevel) {
                addZipOrderValuesAndUpdateNodesToNextLevel(currLevelNodes, sortedValues)
            } else {
                addZagOrderValuesAndUpdateNodesToNextLevel(currLevelNodes, sortedValues)
            }
        }
        return sortedValues
    }

    private fun addZipOrderValuesAndUpdateNodesToNextLevel(
        nodes: ArrayDeque<TreeNode>,
        sortedValues: MutableList<List<Int>>
    ) {
        val zipOrderValues = mutableListOf<Int>()
        repeat(nodes.size) {
            val node = nodes.removeFirst()
            zipOrderValues.add(node.`val`)
            node.left?.let { nodes.addLast(it) }
            node.right?.let { nodes.addLast(it) }
        }
        sortedValues.add(zipOrderValues)
    }

    private fun addZagOrderValuesAndUpdateNodesToNextLevel(
        nodes: ArrayDeque<TreeNode>,
        sortedValues: MutableList<List<Int>>
    ) {
        val zagOrderValues = mutableListOf<Int>()
        repeat(nodes.size) {
            val node = nodes.removeLast()
            zagOrderValues.add(node.`val`)
            node.right?.let { nodes.addFirst(it) }
            node.left?.let { nodes.addFirst(it) }
        }
        sortedValues.add(zagOrderValues)
    }
}