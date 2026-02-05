package com.hj.leetcode.kotlin.problem3379

/**
 * LeetCode page: [3379. Transformed Array](https://leetcode.com/problems/transformed-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun constructTransformedArray(nums: IntArray): IntArray =
        IntArray(nums.size) { i ->
            val j = (i + nums[i]).mod(nums.size)
            nums[j]
        }
}
