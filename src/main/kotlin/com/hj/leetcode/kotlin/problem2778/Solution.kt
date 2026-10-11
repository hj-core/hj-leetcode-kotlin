package com.hj.leetcode.kotlin.problem2778

/**
 * LeetCode page: [2778. Sum of Squares of Special Elements ](https://leetcode.com/problems/sum-of-squares-of-special-elements/);
 */
class Solution {
    // Complexity:
    // Time O(sqrt(N)) and Space O(1) where N is the length of nums.
    fun sumOfSquares(nums: IntArray): Int {
        val n = nums.size
        var i = 1
        var sum = 0
        while (i * i < n) {
            if (n % i == 0) {
                sum += square(nums[i - 1]) + square(nums[(n / i) - 1])
            }
            i++
        }
        if (i * i == n) {
            sum += square(nums[i - 1])
        }

        return sum
    }

    private fun square(x: Int): Int = x * x
}
