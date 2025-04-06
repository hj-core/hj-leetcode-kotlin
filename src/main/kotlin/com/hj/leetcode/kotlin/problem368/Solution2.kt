package com.hj.leetcode.kotlin.problem368

/**
 * LeetCode page: [368. Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/);
 */
class Solution2 {
    // We consider the largest divisible subset with its elements sorted.
    // dp[start]::= the (next, length) of the largest subset starting with sortedNums[start].

    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of nums.
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        val sortedNums = nums.clone().apply { sort() }
        val (dp, root) = computeDpAndRoot(sortedNums)

        return buildResult(sortedNums, dp, root)
    }

    // computeDpAndRoot returns the dp array and a starting index of the largest divisible subsets.
    private fun computeDpAndRoot(sortedNums: IntArray): Pair<Array<IntArray>, Int> {
        val dp = Array(sortedNums.size) { intArrayOf(sortedNums.size, 1) }

        var root = sortedNums.lastIndex
        for (start in sortedNums.lastIndex - 1 downTo 0) {
            for (next in start + 1..<sortedNums.size) {
                if (sortedNums[next] % sortedNums[start] == 0 && dp[start][1] < dp[next][1] + 1) {
                    dp[start][0] = next
                    dp[start][1] = dp[next][1] + 1
                }
            }

            if (dp[root][1] < dp[start][1]) {
                root = start
            }
        }
        return Pair(dp, root)
    }

    // buildResult constructs the largest divisible subset with the given starting index.
    private fun buildResult(
        sortedNums: IntArray,
        dp: Array<IntArray>,
        root: Int,
    ): List<Int> =
        buildList {
            var index = root
            while (index != sortedNums.size) {
                add(sortedNums[index])
                index = dp[index][0]
            }
        }
}
