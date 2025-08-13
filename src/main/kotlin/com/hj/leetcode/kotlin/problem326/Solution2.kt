package com.hj.leetcode.kotlin.problem326

/**
 * LeetCode page: [326. Power of Three](https://leetcode.com/problems/power-of-three/);
 */
class Solution2 {
    // Complexity:
    // Time O(1) and Space O(1).
    fun isPowerOfThree(n: Int): Boolean {
        // 1162261467 is the maximum 3^i <= 2^31-1
        return n > 0 && 1162261467 % n == 0
    }
}
