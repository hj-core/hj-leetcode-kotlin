package com.hj.leetcode.kotlin.problem1980

/**
 * LeetCode page: [1980. Find Unique Binary String](https://leetcode.com/problems/find-unique-binary-string/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of `nums`.
    fun findDifferentBinaryString(nums: Array<String>): String {
        val n = nums.size
        val visited = BooleanArray(n + 1)
        for (str in nums) {
            val num = str.toInt(radix = 2)
            if (num <= n) {
                visited[num] = true
            }
        }

        return (0..n)
            .first { !visited[it] }
            .toString(radix = 2)
            .padStart(n, '0')
    }
}
