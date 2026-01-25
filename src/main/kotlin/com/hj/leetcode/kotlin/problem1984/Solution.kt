package com.hj.leetcode.kotlin.problem1984

/**
 * LeetCode page: [1984. Minimum Difference Between Highest and Lowest of K Scores](https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun minimumDifference(
        nums: IntArray,
        k: Int,
    ): Int {
        val nums = nums.sortedArray()
        return (0..nums.size - k).minOf { nums[it + k - 1] - nums[it] }
    }
}
