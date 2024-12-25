package com.hj.leetcode.kotlin.problem515

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [515. Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(H)
     * where N and H are the number of nodes and height of root, respectively.
     */
    fun largestValues(root: TreeNode?): List<Int> =
        buildList {
            dfs(root, 0) { node, depth ->
                if (size == depth) {
                    add(node.`val`)
                } else {
                    this[depth] = maxOf(this[depth], node.`val`)
                }
            }
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
