package com.hj.leetcode.kotlin.problem3300

/**
 * LeetCode page: [3300. Minimum Element After Replacement With Digit Sum](https://leetcode.com/problems/minimum-element-after-replacement-with-digit-sum/);
 */
class Solution {
    // Complexity:
    // Time O(L) and Space O(1) where L is the total number of digits in nums.
    fun minElement(nums: IntArray): Int = nums.minOf(::digitSum)

    private fun digitSum(num: Int): Int {
        var sum = 0
        var x = num
        while (x != 0) {
            sum += x % 10
            x /= 10
        }

        return sum
    }
}
