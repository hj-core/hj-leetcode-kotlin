package com.hj.leetcode.kotlin.problem1671

/**
 * LeetCode page: [1671. Minimum Number of Removals to Make Mountain Array](https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of nums.
     */
    fun minimumMountainRemovals(nums: IntArray): Int = nums.size - longestMountain(nums)

    private fun longestMountain(nums: IntArray): Int {
        val numsList = nums.asList()
        // longestLeft[i]::= the length of longest left slope if nums[i] is the peak
        val longestLeft = longestLeftSlope(numsList)
        val longestRight = longestLeftSlope(numsList.asReversed()).asReversed()
        return (nums.indices)
            .asSequence()
            .filter { longestLeft[it] > 0 && longestRight[it] > 0 }
            .maxOfOrNull { 1 + longestLeft[it] + longestRight[it] }
            ?: throw IllegalArgumentException("No mountain array.")
    }

    private fun longestLeftSlope(nums: List<Int>): List<Int> {
        // dp[L]::= the minimum last value of length L increasing sequence
        val dp = mutableListOf(0)
        val result = MutableList(nums.size) { 0 }
        for ((i, num) in nums.withIndex()) {
            val insertIndex = dp.binarySearch(num).let { if (it < 0) -(it + 1) else it }
            when {
                insertIndex == dp.size -> {
                    result[i] = dp.size - 1
                    dp.add(num)
                }

                dp[insertIndex] == num -> {
                    result[i] = insertIndex - 1
                }

                insertIndex > 0 -> {
                    result[i] = insertIndex - 1
                    dp[insertIndex] = num
                }

                else -> throw IllegalArgumentException("num= $num: Violate constraints that num >= 1.")
            }
        }
        return result
    }
}
