package com.hj.leetcode.kotlin.problem515

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [515. Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun largestValues(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        dfs(root, 0) { node, depth ->
            if (result.size == depth) {
                result.add(node.`val`)
            } else {
                result[depth] = maxOf(result[depth], node.`val`)
            }
        }
        return result
    }

    private fun dfs(
        node: TreeNode?,
        depth: Int,
        onEachNode: (node: TreeNode, depth: Int) -> Unit,
    ) {
        if (node == null) {
            return
        }
        onEachNode(node, depth)

        dfs(node.left, depth + 1, onEachNode)
        dfs(node.right, depth + 1, onEachNode)
    }
}