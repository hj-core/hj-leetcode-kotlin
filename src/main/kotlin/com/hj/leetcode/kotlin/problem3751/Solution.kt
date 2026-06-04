package com.hj.leetcode.kotlin.problem3751

/**
 * LeetCode page: [3751. Total Waviness of Numbers in Range I](https://leetcode.com/problems/total-waviness-of-numbers-in-range-i/);
 */
class Solution {
    // Complexity:
    // Time O((nums2-nums1) * Log(nums2)) and Space O(1).
    fun totalWaviness(
        num1: Int,
        num2: Int,
    ): Int = (num1..num2).sumOf(::countWaviness)

    private fun countWaviness(num: Int): Int {
        var totalWaviness = 0
        var x = num / 10
        var mid = num % 10
        var right = num % 10

        while (x > 0) {
            val left = x % 10
            val isWavy = (left < mid && right < mid) || (mid < left && mid < right)
            if (isWavy) {
                totalWaviness++
            }

            right = mid
            mid = left
            x /= 10
        }

        return totalWaviness
    }
}
