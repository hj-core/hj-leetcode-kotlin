package com.hj.leetcode.kotlin.problem3010

/**
 * LeetCode page: [3010. Divide an Array Into Subarrays With Minimum Cost I](https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minimumCost(nums: IntArray): Int {
        var a = nums[1]
        var b = nums[2]
        if (b < a) {
            b = a.also { a = b }
        }
        for (i in 3..<nums.size) {
            if (nums[i] < b) {
                b = nums[i]
            }
            if (b < a) {
                b = a.also { a = b }
            }
        }

        return nums[0] + a + b
    }
}
