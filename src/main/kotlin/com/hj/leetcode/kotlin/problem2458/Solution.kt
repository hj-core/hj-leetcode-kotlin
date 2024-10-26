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
        val rootHeight = checkNotNull(subtreeHeights[root])
        // query(node.val) = answers[node.val] ?: root.height
        val answers = answerAllNodes(root, subtreeHeights)
        // check(answers.size <= rootHeight + 1)
        return IntArray(queries.size) {
            answers[queries[it]] ?: rootHeight
        }
    }

    private fun computeSubtreeHeights(root: TreeNode?): Map<TreeNode, Int> =
        buildMap {
            dfsHeight(root, this::put)
        }

    private fun dfsHeight(
        root: TreeNode?,
        onEachNode: (node: TreeNode, height: Int) -> Unit,
    ): Int {
        if (root == null) {
            return -1 // to cancel the plus 1 in height
        }
        val height =
            max(
                dfsHeight(root.left, onEachNode),
                dfsHeight(root.right, onEachNode),
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
            dfsAnswers(root, 0, -1, subtreeHeights, this)
        }

    private fun dfsAnswers(
        root: TreeNode?,
        pathLength: Int,
        maxHeightByLosers: Int, // longest path not passing through the current node
        subtreeHeights: Map<TreeNode, Int>,
        answers: MutableMap<Int, Int>,
    ) {
        if (root == null) {
            return
        }

        val leftHeight = root.left?.let { checkNotNull(subtreeHeights[it]) } ?: -1
        val rightHeight = root.right?.let { checkNotNull(subtreeHeights[it]) } ?: -1
        if (leftHeight == rightHeight) {
            return // delete either child or their descendants doesn't affect the overall height
        }
        val winner = if (leftHeight > rightHeight) root.left else root.right
        checkNotNull(winner)
        val heightByLoser = pathLength + 1 + min(leftHeight, rightHeight)
        val answerWinner = max(maxHeightByLosers, heightByLoser)
        answers[winner.`val`] = answerWinner
        // answerLoserAndItsDescendants = height of the original tree
        dfsAnswers(winner, pathLength + 1, answerWinner, subtreeHeights, answers)
    }
}
