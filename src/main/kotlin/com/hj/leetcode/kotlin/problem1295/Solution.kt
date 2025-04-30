package com.hj.leetcode.kotlin.problem1295

/**
 * LeetCode page: [1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(1) where N and M are the length and the average
    // digits of nums, respectively.
    fun findNumbers(nums: IntArray): Int = nums.count(::isEvenDigits)

    private fun isEvenDigits(num: Int): Boolean = countDigits(num) and 1 == 0

    private fun countDigits(num: Int): Int {
        require(num > 0) { "problem constraints have been changed" }
        var result = 0
        var temp = num
        while (temp > 0) {
            result++
            temp /= 10
        }
        return result
    }
}
