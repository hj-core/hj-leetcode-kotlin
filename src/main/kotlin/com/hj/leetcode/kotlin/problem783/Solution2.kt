package com.hj.leetcode.kotlin.problem783

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [783. Minimum Distance Between BST Nodes](https://leetcode.com/problems/minimum-distance-between-bst-nodes/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun minDiffInBST(root: TreeNode?): Int {
        requireNotNull(root) { "Constraint of the problem is not satisfied." }

        val nodeStack = ArrayDeque<TreeNode>()
        var currNode = root
        var prevNode: TreeNode? = null
        var minDiff: Int? = null

        while (nodeStack.isNotEmpty() || currNode != null) {
            while (currNode != null) {
                nodeStack.addLast(currNode)
                currNode = currNode.left
            }

            currNode = nodeStack.removeLast()
            prevNode?.let {
                val diff = checkNotNull(currNode).`val` - it.`val`
                minDiff = minOf(minDiff ?: diff, diff)
            }
            prevNode = currNode
            currNode = currNode.right
        }
        return checkNotNull(minDiff)
    }
}