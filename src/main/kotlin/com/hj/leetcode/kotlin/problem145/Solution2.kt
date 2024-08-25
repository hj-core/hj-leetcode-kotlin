package com.hj.leetcode.kotlin.problem145

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/);
 */
class Solution2 {
    /* Complexity:
     * Timme O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun postorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return emptyList()
        }
        val stack1 = mutableListOf<Int>()
        val stack2 = mutableListOf<TreeNode>()
        stack2.add(root)
        while (stack2.isNotEmpty()) {
            val node = stack2.removeLast()
            stack1.add(node.`val`)
            node.left?.let { stack2.add(it) }
            node.right?.let { stack2.add(it) }
        }
        return stack1.reversed()
    }
}
