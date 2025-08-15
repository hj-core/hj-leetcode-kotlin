package com.hj.leetcode.kotlin.problem342

/**
 * LeetCode page: [342. Power of Four](https://leetcode.com/problems/power-of-four/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun isPowerOfFour(n: Int): Boolean = n and 0x55555555 != 0 && n and (n - 1) == 0
}
