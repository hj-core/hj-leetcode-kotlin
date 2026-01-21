package com.hj.leetcode.kotlin.problem3315

/**
 * LeetCode page: [3315. Construct the Minimum Bitwise Array II](https://leetcode.com/problems/construct-the-minimum-bitwise-array-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minBitwiseArray(nums: List<Int>): IntArray =
        IntArray(nums.size) { i ->
            if (nums[i] == 2) {
                -1
            } else {
                (nums[i] + (nums[i] and (nums[i] + 1))) shr 1
            }
        }
}
