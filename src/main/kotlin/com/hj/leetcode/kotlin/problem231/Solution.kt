package com.hj.leetcode.kotlin.problem231

/**
 * LeetCode page: [231. Power of Two](https://leetcode.com/problems/power-of-two/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of n;
     */
    fun isPowerOfTwo(n: Int): Boolean {
        return n > 0 && (n and (n - 1) == 0)
    }
}