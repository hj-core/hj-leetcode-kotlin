package com.hj.leetcode.kotlin.problem2265

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2265. Count Nodes Equal to Average of Subtree](https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun averageOfSubtree(root: TreeNode?): Int {
        var result = 0

        postOrderTraversal(root) { nodeValue, augmentation ->
            val subTreeAverage = augmentation.let { it.subTreeSum / it.subTreeSize }
            if (nodeValue == subTreeAverage) {
                result++
            }
        }
        return result
    }

    private fun postOrderTraversal(
        root: TreeNode?,
        onEachNode: (nodeValue: Int, augmentation: NodeAugmentation) -> Unit,
    ): NodeAugmentation {

        if (root == null) {
            return NodeAugmentation(0, 0)
        }

        val leftAugmentation = postOrderTraversal(root.left, onEachNode)
        val rightAugmentation = postOrderTraversal(root.right, onEachNode)

        val result = NodeAugmentation(
            1 + leftAugmentation.subTreeSize + rightAugmentation.subTreeSize,
            root.`val` + leftAugmentation.subTreeSum + rightAugmentation.subTreeSum
        )

        onEachNode(root.`val`, result)
        return result
    }

    private data class NodeAugmentation(val subTreeSize: Int, val subTreeSum: Int)
}