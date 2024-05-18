package com.hj.leetcode.kotlin.problem979

import com.hj.leetcode.kotlin.common.model.TreeNode
import kotlin.math.abs

/**
 * LeetCode page: [979. Distribute Coins in Binary Tree](https://leetcode.com/problems/distribute-coins-in-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun distributeCoins(root: TreeNode?): Int {
        var result = 0
        dfs(root) { augment ->
            result += abs(augment.size - augment.totalCoins)
        }
        return result
    }

    private fun dfs(
        root: TreeNode?,
        onEachNode: (augment: TreeAugmentation) -> Unit,
    ): TreeAugmentation {
        if (root == null) {
            return TreeAugmentation(0, 0)
        }
        val leftAugment = dfs(root.left, onEachNode)
        val rightAugment = dfs(root.right, onEachNode)
        val result = TreeAugmentation(
            1 + leftAugment.size + rightAugment.size,
            root.`val` + leftAugment.totalCoins + rightAugment.totalCoins
        )
        onEachNode(result)
        return result
    }

    private data class TreeAugmentation(val size: Int, val totalCoins: Int)
}