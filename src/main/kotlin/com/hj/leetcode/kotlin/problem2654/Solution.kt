package com.hj.leetcode.kotlin.problem2654

/**
 * LeetCode page: [2654. Minimum Number of Operations to Make All Array Elements Equal to 1](https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/);
 */
class Solution {
    // Complexity:
    // Time O((N^2)*LogM) and Space O(1) where N is the length of
    // nums and M is the maximum element in nums.
    fun minOperations(nums: IntArray): Int {
        val ones = nums.count { it == 1 }
        if (ones > 1) {
            return nums.size - ones
        }

        var result = nums.size * 2
        for ((i, num) in nums.withIndex()) {
            var arrGcd = num
            var j = i + 1
            while (j < nums.size && arrGcd != 1) {
                arrGcd = gcd(arrGcd, nums[j])
                j++
            }

            if (arrGcd == 1) {
                result = minOf(result, j - i - 2 + nums.size)
            } else {
                break
            }
        }

        if (result == nums.size * 2) {
            return -1
        }
        return result
    }

    private fun gcd(
        a: Int,
        b: Int,
    ): Int {
        var x = a
        var y = b
        while (x != 0) {
            x = (y % x).also { y = x }
        }
        return y
    }
}
