package com.hj.leetcode.kotlin.problem2196

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2196. Create Binary Tree From Descriptions](https://leetcode.com/problems/create-binary-tree-from-descriptions/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of description;
     */
    fun createBinaryTree(descriptions: Array<IntArray>): TreeNode? {
        // Node value to the node object
        val nodes = mutableMapOf<Int, TreeNode>()

        // Build the tree
        for ((parent, child, isLeft) in descriptions) {
            val parentNode = nodes.computeIfAbsent(parent) { TreeNode(parent) }
            val childNode = nodes.computeIfAbsent(child) { TreeNode(child) }
            if (isLeft == 1) {
                parentNode.left = childNode
            } else {
                parentNode.right = childNode
            }
        }

        // Find the root
        for ((_, child, _) in descriptions) {
            nodes.remove(child)
        }
        check(nodes.size == 1)
        return nodes.values.first()
    }
}