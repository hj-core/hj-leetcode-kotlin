package com.hj.leetcode.kotlin.problem653

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [653. Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        if (root == null || root.isLeaf()) return false

        val inOrderIterator = BstIterator.inOrder(root)
        val reverseOrderIterator = BstIterator.reverseOrder(root)

        var left = inOrderIterator.next()
        var right = reverseOrderIterator.next()

        while (left != right) {
            val currSum = left.`val` + right.`val`

            when {
                currSum < k -> left = inOrderIterator.next()
                currSum > k -> right = reverseOrderIterator.next()
                else -> return true
            }
        }
        return false
    }

    private fun TreeNode.isLeaf() = left == null && right == null

    private class BstIterator private constructor(root: TreeNode, private val reverse: Boolean) {

        private val nodeStack = ArrayDeque<TreeNode>()

        private val getChildCorrespondingToOrder: (parentNode: TreeNode) -> TreeNode? =
            if (reverse) { parentNode -> parentNode.right } else { parentNode -> parentNode.left }

        init {
            updateNodeStack(root)
        }

        private fun updateNodeStack(parentNode: TreeNode?) {
            var currParentNode = parentNode

            while (currParentNode != null) {
                nodeStack.addFirst(currParentNode)
                currParentNode = getChildCorrespondingToOrder(currParentNode)
            }
        }

        fun hasNext() = nodeStack.isNotEmpty()

        fun next(): TreeNode {
            val popped = nodeStack.removeFirst()

            val newParentNode = if (reverse) popped.left else popped.right
            updateNodeStack(newParentNode)

            return popped
        }

        companion object {
            fun inOrder(bstRoot: TreeNode) = BstIterator(bstRoot, false)

            fun reverseOrder(bstRoot: TreeNode) = BstIterator(bstRoot, true)
        }
    }
}