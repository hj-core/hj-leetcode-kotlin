package com.hj.leetcode.kotlin.problem113

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [113. Path Sum II](https://leetcode.com/problems/path-sum-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the nodes and height of root;
     */
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        val ans = mutableListOf<List<Int>>()
        if (root == null) return ans

        addPathsWithTargetSum(root, targetSum, ans)
        return ans
    }

    private fun addPathsWithTargetSum(
        node: TreeNode,
        targetSum: Int,
        container: MutableList<List<Int>>,
        accList: MutableList<Int> = mutableListOf(),
        accSum: Int = 0
    ) {
        accList.add(node.`val`)
        val newAccSum = accSum + node.`val`

        if (node.isLeaf()) {
            if (newAccSum == targetSum) {
                val copy = accList.toList()
                container.add(copy)
            }
        } else {
            node.left?.let { addPathsWithTargetSum(it, targetSum, container, accList, newAccSum) }
            node.right?.let { addPathsWithTargetSum(it, targetSum, container, accList, newAccSum) }
        }

        accList.removeAt(accList.lastIndex)
    }

    private fun TreeNode.isLeaf() = left == null && right == null
}