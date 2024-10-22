package com.hj.leetcode.kotlin.problem2583

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2583. Kth Largest Sum in a Binary Tree](https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time Expected O(N+H) but Worst O(N+H^2), and Space O(H)
     * where N is the number of nodes in root and H is the height of root.
     */
    fun kthLargestLevelSum(
        root: TreeNode?,
        k: Int,
    ): Long {
        require(k > 0)
        val levelSums = computeLevelSums(root)
        return when {
            levelSums.size < k -> -1
            k == 1 -> levelSums.max()
            k == levelSums.size -> levelSums.min()
            else -> findKthLargest(levelSums, k, levelSums.indices)
        }
    }

    private fun computeLevelSums(root: TreeNode?): MutableList<Long> {
        val result = mutableListOf<Long>()
        dfs(root, 0) { node, depth ->
            if (result.size == depth) {
                result.add(0L)
            }
            result[depth] = result[depth] + node.`val`
        }
        return result
    }

    private fun dfs(
        root: TreeNode?,
        depth: Int,
        onEachNode: (node: TreeNode, depth: Int) -> Unit,
    ) {
        if (root == null) {
            return
        }
        onEachNode(root, depth)
        dfs(root.left, depth + 1, onEachNode)
        dfs(root.right, depth + 1, onEachNode)
    }

    private fun findKthLargest(
        nums: MutableList<Long>,
        k: Int,
        indexRange: IntRange,
    ): Long {
        require(k in 1..(indexRange.last - indexRange.first + 1))
        // Quick select
        val pivot = nums[indexRange.random()]
        var left = indexRange.first
        var right = indexRange.last
        while (left <= right) {
            if (nums[left] < pivot) {
                left += 1
            } else {
                nums[left] = nums[right].also { nums[right] = nums[left] }
                right -= 1
            }
        }

        val rightSize = indexRange.last - right
        return when {
            rightSize < k -> findKthLargest(nums, k - rightSize, indexRange.first..right)
            rightSize > k -> findKthLargest(nums, k, left..indexRange.last)
            else -> pivot
        }
    }
}
