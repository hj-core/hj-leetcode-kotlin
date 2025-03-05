package com.hj.leetcode.kotlin.problem2579

/**
 * LeetCode page: [2579. Count Total Number of Colored Cells](https://leetcode.com/problems/count-total-number-of-colored-cells/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun coloredCells(n: Int): Long = 1 + 2L * n * (n - 1)
}
