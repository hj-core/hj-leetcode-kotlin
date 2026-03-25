package com.hj.leetcode.kotlin.problem3546

/**
 * LeetCode page: [3546. Equal Sum Grid Partition I](https://leetcode.com/problems/equal-sum-grid-partition-i/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(M+N) where M and N are the number of rows and
    // columns in grid, respectively.
    fun canPartitionGrid(grid: Array<IntArray>): Boolean {
        val m = grid.size
        val n = grid[0].size

        var totalSum = 0L
        val rowSum = LongArray(m)
        val colSum = LongArray(n)
        for ((r, row) in grid.withIndex()) {
            for ((c, v) in row.withIndex()) {
                rowSum[r] += v
                colSum[c] += v
            }
            totalSum += rowSum[r]
        }

        if (totalSum % 2 != 0L) {
            return false
        }

        val targetSum = totalSum / 2
        return hasPrefixSum(rowSum, targetSum) ||
            hasPrefixSum(colSum, targetSum)
    }

    private fun hasPrefixSum(
        nums: LongArray,
        targetSum: Long,
    ): Boolean {
        var prefixSum = 0L
        for (num in nums) {
            prefixSum += num
            if (prefixSum == targetSum) {
                return true
            }
        }

        return false
    }
}
