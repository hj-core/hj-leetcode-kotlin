package com.hj.leetcode.kotlin.problem3550

/**
 * LeetCode page: [3550. Smallest Index With Digit Sum Equal to Index](https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/);
 */
class Solution {
    private val digitSum = precomputeDigitSum()

    fun smallestIndex(nums: IntArray): Int {
        val maxDigitSum = 27
        val maxIndex = minOf(nums.size - 1, maxDigitSum)
        return (0..maxIndex).firstOrNull { it == digitSum[nums[it]] } ?: -1
    }

    private fun precomputeDigitSum(): IntArray {
        val sum = IntArray(1001) // the maximum num is 1000

        var headUnit = 1
        while (headUnit < 1000) {
            for (headDigit in 1..<10) {
                val head = headDigit * headUnit
                for (tail in 0..<headUnit) {
                    sum[head + tail] = headDigit + sum[tail]
                }
            }
            headUnit *= 10
        }
        sum[1000] = 1

        return sum
    }
}
