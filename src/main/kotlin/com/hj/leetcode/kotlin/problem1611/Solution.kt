package com.hj.leetcode.kotlin.problem1611

/**
 * LeetCode page: [1611. Minimum One Bit Operations to Make Integers Zero](https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun minimumOneBitOperations(n: Int): Int {
        var result = 0
        var bit = 1
        while (bit <= n) {
            if (bit and n != 0) {
                result = (bit shl 1) - 1 - result
            }
            bit = bit shl 1
        }
        return result
    }
}
