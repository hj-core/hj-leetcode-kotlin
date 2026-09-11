package com.hj.leetcode.kotlin.problem2265

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2265. Count Nodes Equal to Average of Subtree](https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(H) where N is the size of root and H is the height of root.
    fun averageOfSubtree(root: TreeNode?): Int {
        var count = 0
        dfs(root) { nodeVal, size, sum ->
            if (sum / size == nodeVal) {
                count++
            }
        }
        return count
    }

    private fun dfs(
        root: TreeNode?,
        onEachNode: (nodeVal: Int, size: Int, sum: Int) -> Unit,
    ): Pair<Int, Int> {
        if (root == null) {
            return Pair(0, 0)
        }

        val (leftSize, leftSum) = dfs(root.left, onEachNode)
        val (rightSize, rightSum) = dfs(root.right, onEachNode)
        val subTreeSize = 1 + leftSize + rightSize
        val subtreeSum = root.`val` + leftSum + rightSum
        onEachNode(root.`val`, subTreeSize, subtreeSum)
        return Pair(subTreeSize, subtreeSum)
    }
}
