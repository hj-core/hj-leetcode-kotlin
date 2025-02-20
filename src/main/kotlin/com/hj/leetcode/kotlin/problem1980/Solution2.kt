package com.hj.leetcode.kotlin.problem1980

/**
 * LeetCode page: [1980. Find Unique Binary String](https://leetcode.com/problems/find-unique-binary-string/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of `nums`.
    fun findDifferentBinaryString(nums: Array<String>): String =
        buildString {
            for (i in nums.indices) {
                append(if (nums[i][i] == '0') '1' else '0')
            }
        }
}
