package com.hj.leetcode.kotlin.problem1609

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1609. Even Odd Tree](https://leetcode.com/problems/even-odd-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun isEvenOddTree(root: TreeNode?): Boolean {
        // the most recent values for each level or null if it violates the rule
        val indicators = mutableListOf<Int?>()
        preorderTraversal(root, 0) { value, depth ->
            when {
                depth == indicators.size -> {
                    if (depth and 1 == value and 1) {
                        indicators.add(null)
                    } else {
                        indicators.add(value)
                    }
                }

                indicators[depth] == null -> {}

                depth and 1 == 0 -> {
                    if (value and 1 == 1 && checkNotNull(indicators[depth]) < value) {
                        indicators[depth] = value
                    } else {
                        indicators[depth] = null
                    }
                }

                depth and 1 == 1 -> {
                    if (value and 1 == 0 && value < checkNotNull(indicators[depth])) {
                        indicators[depth] = value
                    } else {
                        indicators[depth] = null
                    }
                }
            }
        }
        return indicators.none { it == null }
    }

    private fun preorderTraversal(
     node: TreeNode?,
     depth: Int,
     onEachNode: (nodeValue: Int, depth: Int) -> Unit,
    ) {
        if (node == null) {
            return
        }
        onEachNode(node.`val`, depth)
        preorderTraversal(node.left, depth + 1, onEachNode)
        preorderTraversal(node.right, depth + 1, onEachNode)
    }
}