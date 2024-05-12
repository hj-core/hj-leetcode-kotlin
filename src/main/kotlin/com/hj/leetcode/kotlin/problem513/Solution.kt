package com.hj.leetcode.kotlin.problem513

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [513. Find Bottom Left Tree Value](https://leetcode.com/problems/find-bottom-left-tree-value/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root and
     * H is the height of root;
     */
    fun findBottomLeftValue(root: TreeNode?): Int {
        var result = -1
        var resultDepth = -1
        preorderTraversal(root, 0) { value, depth ->
            if (depth > resultDepth) {
                result = value
                resultDepth = depth
            }
        }
        return result
    }

    private fun preorderTraversal(
        node: TreeNode?,
        depth: Int,
        onEachNode: (value: Int, depth: Int) -> Unit,
    ) {
        if (node == null) {
            return
        }

        onEachNode(node.`val`, depth)
        preorderTraversal(node.left, depth + 1, onEachNode)
        preorderTraversal(node.right, depth + 1, onEachNode)
    }
}