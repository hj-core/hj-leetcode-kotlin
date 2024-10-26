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
        // query(node.val) = answers[node.val] ?: root.height
        val answers = answerAllNodes(root, subtreeHeights)
        val rootHeight = checkNotNull(subtreeHeights[root])
        return IntArray(queries.size) {
            answers[queries[it]] ?: rootHeight
        }
    }

    private fun computeSubtreeHeights(root: TreeNode?): Map<TreeNode, Int> =
        buildMap {
            computeHeight(root) { node, height ->
                put(node, height)
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
        subtreeHeights: Map<TreeNode, Int>,
    ): MutableMap<Int, Int> =
        mutableMapOf<Int, Int>().apply {
            put(root.`val`, -1)
            updateAnswers(root, 0, 0, subtreeHeights, this)
        }

    private fun updateAnswers(
        root: TreeNode?,
        pathLength: Int,
        candidateHeight: Int, // maximum height that does not contain the current root
        subtreeHeights: Map<TreeNode, Int>,
        answers: MutableMap<Int, Int>,
    ) {
        if (root == null) {
            return
        }

        val leftHeight = root.left?.let { checkNotNull(subtreeHeights[it]) } ?: -1
        val rightHeight = root.right?.let { checkNotNull(subtreeHeights[it]) } ?: -1
        if (leftHeight == rightHeight) {
            return // delete either subtree doesn't affect the overall height
        }
        val winner = if (leftHeight > rightHeight) root.left else root.right
        checkNotNull(winner)
        val answerWinner = max(candidateHeight, pathLength + 1 + min(leftHeight, rightHeight))
        answers[winner.`val`] = answerWinner
        // answerLoser = height of the original tree
        updateAnswers(winner, pathLength + 1, answerWinner, subtreeHeights, answers)
    }
}
