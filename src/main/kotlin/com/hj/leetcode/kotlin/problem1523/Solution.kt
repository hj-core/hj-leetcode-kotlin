package com.hj.leetcode.kotlin.problem1523

/**
 * LeetCode page: [1523. Count Odd Numbers in an Interval Range](https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun countOdds(
        low: Int,
        high: Int,
    ): Int = (high - low + 1 + (low and 1)) / 2
}
