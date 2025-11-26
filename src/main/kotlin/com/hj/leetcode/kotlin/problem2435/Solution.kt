package com.hj.leetcode.kotlin.problem2435

/**
 * LeetCode page: [2435. Paths in Matrix Whose Sum Is Divisible by K](https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/);
 */
class Solution {
    // Complexity:
    // Time O(kMN) and Space O(kN) where M and N are the number of
    // rows and columns in grid, respectively.
    fun numberOfPaths(
        grid: Array<IntArray>,
        k: Int,
    ): Int {
        val module = 1_000_000_007
        val m = grid.size
        val n = grid[0].size

        // dp[c][i]@r:= the number of paths from (0, 0) to (r, c)
        // whose path-sum mod k equals r.
        val dp = Array(n) { IntArray(k) }

        dp[0][0] = 1
        for (r in 0..<m) {
            rotate(dp[0], grid[r][0] % k)

            for (c in 1..<n) {
                for (i in 0..<k) {
                    dp[c][i] = (dp[c][i] + dp[c - 1][i]) % module
                }
                rotate(dp[c], grid[r][c] % k)
            }
        }
        return dp[n - 1][0]
    }

    private fun rotate(
        nums: IntArray,
        shift: Int,
    ) {
        val netShift = shift % nums.size
        reverse(nums, 0..<netShift)
        reverse(nums, netShift..<nums.size)
        reverse(nums, nums.indices)
    }

    private fun reverse(
        nums: IntArray,
        indexRange: IntRange,
    ) {
        var i = indexRange.first
        var j = indexRange.last
        while (i < j) {
            nums[i] = nums[j].also { nums[j] = nums[i] }
            i++
            j--
        }
    }
}
