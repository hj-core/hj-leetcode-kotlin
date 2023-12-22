package com.hj.leetcode.kotlin.problem94

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun inorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return emptyList()
        }

        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode>()
        var currentNode: TreeNode? = root

        while (currentNode != null) {
            stack.addLast(currentNode)
            currentNode = currentNode.left
        }

        while (stack.isNotEmpty()) {
            currentNode = stack.removeLast()
            result.add(currentNode.`val`)

            currentNode = currentNode.right
            while (currentNode != null) {
                stack.addLast(currentNode)
                currentNode = currentNode.left
            }
        }
        return result
    }
}