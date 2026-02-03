package com.hj.leetcode.kotlin.problem3637

/**
 * LeetCode page: [3637. Trionic Array I](https://leetcode.com/problems/trionic-array-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun isTrionic(nums: IntArray): Boolean {
        // Strictly increasing
        var p1 = 0
        while (p1 < nums.size - 3 && nums[p1] < nums[p1 + 1]) {
            p1++
        }
        if (p1 == 0) {
            return false
        }

        // Strictly decreasing
        var p2 = p1
        while (p2 < nums.size - 2 && nums[p2] > nums[p2 + 1]) {
            p2++
        }
        if (p2 == p1) {
            return false
        }

        // Strictly increasing
        var p3 = p2
        while (p3 < nums.size - 1 && nums[p3] < nums[p3 + 1]) {
            p3++
        }

        return p3 == nums.size - 1
    }
}
