package com.hj.leetcode.kotlin.problem1752

/**
 * LeetCode page: [1752. Check if Array Is Sorted and Rotated](https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun check(nums: IntArray): Boolean {
        var left = 0
        while (left < nums.lastIndex && nums[left] <= nums[left + 1]) {
            left++
        }

        if (left != nums.lastIndex && nums.last() <= nums[0]) {
            left++
            while (left < nums.lastIndex && nums[left] <= nums[left + 1]) {
                left++
            }
        }
        return left == nums.lastIndex
    }
}
