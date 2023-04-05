package com.hj.leetcode.kotlin.problem2439

/**
 * LeetCode page: [2439. Minimize Maximum of Array](https://leetcode.com/problems/minimize-maximum-of-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun minimizeArrayValue(nums: IntArray): Int {
        var minValue = 0L
        var prefixSum = 0L
        for ((index, num) in nums.withIndex()) {
            prefixSum += num
            val size = index + 1
            val ceilingAverage = prefixSum.ceilingDivide(size)
            minValue = maxOf(minValue, ceilingAverage)
        }
        return minValue.toInt()
    }

    private fun Long.ceilingDivide(other: Int): Long {
        return (this + other - 1) / other
    }
}