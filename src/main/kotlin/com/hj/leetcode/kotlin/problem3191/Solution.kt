package com.hj.leetcode.kotlin.problem3191

/**
 * LeetCode page: [3191. Minimum Operations to Make Binary Array Elements Equal to One I](https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minOperations(nums: IntArray): Int {
        var windowBits = 0 // Only the last three bits are effective
        for (i in 0..<3) {
            windowBits = (windowBits shl 1) xor nums[i]
        }

        val startMask = 0b100 // Mask to extract the bit at the window start
        var result = 0
        for (i in 3..<nums.size) {
            if (windowBits and startMask == 0) {
                windowBits = windowBits.inv()
                result++
            }
            windowBits = (windowBits shl 1) xor nums[i]
        }

        val windowMask = 0b111 // Mask to extract the effective window bits
        return when (windowBits and windowMask) {
            0 -> result + 1
            windowMask -> result
            else -> -1
        }
    }
}
