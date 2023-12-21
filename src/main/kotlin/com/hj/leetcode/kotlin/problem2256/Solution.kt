package com.hj.leetcode.kotlin.problem2256

/**
 * LeetCode page: [2256. Minimum Average Difference](https://leetcode.com/problems/minimum-average-difference/);
 */
class Solution {
    /* Complexity:
     * Time O(|nums|) and Space O(1);
     */
    fun minimumAverageDifference(nums: IntArray): Int {
        val totalSum = nums.fold(0L) { acc, i -> acc + i }
        var leftSum = 0L
        var minAbsAverageDiff = Long.MAX_VALUE
        var indexOfMin = -1

        for ((index, num) in nums.withIndex()) {
            leftSum += num
            val leftSize = index + 1
            val leftAverage = leftSum / leftSize

            val rightSum = totalSum - leftSum
            val rightSize = nums.size - leftSize
            val rightAverage = if (rightSize == 0) 0 else rightSum / rightSize

            val absAverageDiff = Math.abs(leftAverage - rightAverage)
            if (absAverageDiff < minAbsAverageDiff) {
                minAbsAverageDiff = absAverageDiff
                indexOfMin = index
            }
        }
        return indexOfMin
    }
}