package com.hj.leetcode.kotlin.problem3151

/**
 * LeetCode page: [3151. Special Array I](https://leetcode.com/problems/special-array-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun isArraySpecial(nums: IntArray): Boolean =
        (0..<nums.lastIndex).all {
            (nums[it] xor nums[it + 1]) and 1 == 1
        }
}
