package com.hj.leetcode.kotlin.problem3392

/**
 * LeetCode page: [3392. Count Subarrays of Length Three With a Condition](https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun countSubarrays(nums: IntArray): Int =
        (1..<nums.lastIndex).count {
            nums[it] == (nums[it - 1] + nums[it + 1]) * 2
        }
}
