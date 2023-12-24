package com.hj.leetcode.kotlin.problem896

/**
 * LeetCode page: [896. Monotonic Array](https://leetcode.com/problems/monotonic-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun isMonotonic(nums: IntArray): Boolean {
        val criteria = when {
            nums.first() < nums.last() -> { a: Int, b: Int -> a <= b }
            nums.first() > nums.last() -> { a: Int, b: Int -> a >= b }
            else -> { a : Int, b : Int -> a == b }
        }

        for (index in 1 until nums.size) {
            if (!criteria(nums[index - 1], nums[index])) {
                return false
            }
        }
        return true
    }
}