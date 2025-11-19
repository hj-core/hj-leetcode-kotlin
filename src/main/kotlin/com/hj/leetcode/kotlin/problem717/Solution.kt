package com.hj.leetcode.kotlin.problem717

/**
 * LeetCode page: [717. 1-bit and 2-bit Characters](https://leetcode.com/problems/1-bit-and-2-bit-characters/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of bits.
    fun isOneBitCharacter(bits: IntArray): Boolean {
        var i = bits.size - 2
        while (i >= 0 && bits[i] == 1) {
            i--
        }
        return (bits.size - i) and 1 == 0
    }
}
