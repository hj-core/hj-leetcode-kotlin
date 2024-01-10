package com.hj.leetcode.kotlin.problem2385

import com.hj.leetcode.kotlin.common.model.TreeNode
import kotlin.math.abs
import kotlin.math.max

/**
 * LeetCode page: [2385. Amount of Time for Binary Tree to Be Infected](https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun amountOfTime(root: TreeNode?, start: Int): Int {
        requireNotNull(root)
        var result = 0
        dfsHeight(root, start) { result = max(result, it) }
        return result
    }

    private fun dfsHeight(
        root: TreeNode,
        start: Int,
        update: (infectionTime: Int) -> Unit,
    ): Int {

        val heightsByChild = listOf(root.left, root.right).map { child ->
            child
                ?.let { dfsHeight(it, start, update) }
                ?.let { if (it < 0) it - 1 else it + 1 }
                ?.let { if (child.`val` == start) -1 else it }
                ?: 0
        }

        return if (heightsByChild.all { it >= 0 }) {
            heightsByChild
                .max()
                .also { if (root.`val` == start) update(it) }
        } else {
            update(abs(heightsByChild[0] - heightsByChild[1]))
            heightsByChild.min()
        }
    }
}