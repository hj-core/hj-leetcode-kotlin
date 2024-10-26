package com.hj.leetcode.kotlin.problem2458

import com.hj.leetcode.kotlin.common.model.TreeNode
import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [2458. Height of Binary Tree After Subtree Removal Queries](https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N is the number of nodes in root
     * and M is the size of queries.
     */
    fun treeQueries(
        root: TreeNode?,
        queries: IntArray,
    ): IntArray {
        requireNotNull(root)
        val subtreeHeights = computeSubtreeHeights(root)
        // query(node.val) = answerAllNodes[node.val] ?: root.height
        val heightIfDelete = answerAllNodes(root, subtreeHeights)
        val rootHeight = checkNotNull(subtreeHeights[root.`val`])
        return IntArray(queries.size) {
            heightIfDelete[queries[it]] ?: rootHeight
        }
    }

    private fun computeSubtreeHeights(root: TreeNode?): Map<Int, Int> =
        buildMap {
            computeHeight(root) { node, height ->
                put(node.`val`, height)
            }
        }

    private fun computeHeight(
        root: TreeNode?,
        onEachNode: (node: TreeNode, height: Int) -> Unit,
    ): Int {
        if (root == null) {
            return -1
        }
        val height =
            max(
                computeHeight(root.left, onEachNode),
                computeHeight(root.right, onEachNode),
            ) + 1
        onEachNode(root, height)
        return height
    }

    private fun answerAllNodes(
        root: TreeNode,
        subtreeHeights: Map<Int, Int>,
    ): MutableMap<Int, Int> =
        mutableMapOf<Int, Int>().apply {
            put(root.`val`, 0)
            updateHeightIfDelete(root, 0, 0, subtreeHeights, this)
        }

    private fun updateHeightIfDelete(
        root: TreeNode?,
        pathLength: Int,
        candidateHeight: Int,
        subtreeHeights: Map<Int, Int>,
        heightIfDelete: MutableMap<Int, Int>,
    ) {
        if (root == null) {
            return
        }

        val leftHeight = root.left?.let { checkNotNull(subtreeHeights[it.`val`]) } ?: -1
        val rightHeight = root.right?.let { checkNotNull(subtreeHeights[it.`val`]) } ?: -1
        if (leftHeight == rightHeight) {
            return
        }
        val winner = if (leftHeight > rightHeight) root.left else root.right
        checkNotNull(winner)
        val queryWinner = max(candidateHeight, 1 + pathLength + min(leftHeight, rightHeight))
        heightIfDelete[winner.`val`] = queryWinner
        updateHeightIfDelete(winner, pathLength + 1, queryWinner, subtreeHeights, heightIfDelete)
    }
}
