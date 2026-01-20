package com.hj.leetcode.kotlin.problem3314

/**
 * LeetCode page: [3314. Construct the Minimum Bitwise Array I](https://leetcode.com/problems/construct-the-minimum-bitwise-array-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minBitwiseArray(nums: List<Int>): IntArray =
        IntArray(nums.size) { i ->
            if (nums[i] == 2) {
                -1
            } else {
                val m = nums[i] and (nums[i] + 1)
                m + ((nums[i] - m) shr 1)
            }
        }
}
