package com.hj.leetcode.kotlin.problem231

/**
 * LeetCode page: [231. Power of Two](https://leetcode.com/problems/power-of-two/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun isPowerOfTwo(n: Int): Boolean = n > 0 && (n and (n - 1) == 0)
}
