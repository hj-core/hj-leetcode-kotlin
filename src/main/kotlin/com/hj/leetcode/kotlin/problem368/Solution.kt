package com.hj.leetcode.kotlin.problem368

/**
 * LeetCode page: [368. Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of nums;
     */
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        val sorted = nums.sorted()
        /* dp[i]::= the (size, rightmost index in sorted of the second-largest element) of
         * the largest divisible subset of sorted.slice(0..i) that includes sorted[i].
         */
        val dp = buildDp(sorted)

        return buildResult(dp, sorted)
    }

    private fun buildDp(numsSorted: List<Int>): Array<Properties> {
        val result = Array(numsSorted.size) { Properties(1, -1) }

        for (i in result.indices) {
            var j = i - 1
            while (j + 2 > result[i].size) {
                if (numsSorted[i] % numsSorted[j] == 0 && 1 + result[j].size > result[i].size) {
                    result[i] = Properties(1 + result[j].size, j)
                }
                j -= 1
            }
        }
        return result
    }

    private data class Properties(val size: Int, val prevIndex: Int)

    private fun buildResult(dp: Array<Properties>, numsSorted: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        var prevIndex = dp.indices.maxBy { dp[it].size }
        while (prevIndex != -1) {
            result.add(numsSorted[prevIndex])
            prevIndex = dp[prevIndex].prevIndex
        }
        return result
    }
}