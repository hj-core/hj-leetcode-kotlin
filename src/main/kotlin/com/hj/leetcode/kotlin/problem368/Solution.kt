package com.hj.leetcode.kotlin.problem368

/**
 * LeetCode page: [368. Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/);
 */
class Solution {
    // We consider the largest divisible subset with its elements sorted.
    // dp[end]::= the (prev, length) of the largest subset ending with sortedNums[end].

    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of nums.
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        val sorted = nums.clone().apply { sort() }
        val (dp, tail) = computeDpAndTail(sorted)

        return buildResult(sorted, dp, tail)
    }

    // computeDpAndTail returns the dp array and an ending index of the largest divisible subsets.
    private fun computeDpAndTail(sortedNums: IntArray): Pair<Array<IntArray>, Int> {
        val dp = Array(sortedNums.size) { intArrayOf(-1, 1) }

        var tail = 0
        for (end in dp.indices) {
            var prev = end - 1
            while (dp[end][1] < prev + 2) {
                if (sortedNums[end] % sortedNums[prev] == 0 && dp[prev][1] + 1 > dp[end][1]) {
                    dp[end][0] = prev
                    dp[end][1] = dp[prev][1] + 1
                }
                prev--
            }

            if (dp[tail][1] < dp[end][1]) {
                tail = end
            }
        }
        return Pair(dp, tail)
    }

    // buildResult constructs the largest divisible subset with the given ending index.
    private fun buildResult(
        sortedNums: IntArray,
        dp: Array<IntArray>,
        tail: Int,
    ): List<Int> =
        buildList {
            var index = tail
            while (index != -1) {
                add(sortedNums[index])
                index = dp[index][0]
            }
        }
}
