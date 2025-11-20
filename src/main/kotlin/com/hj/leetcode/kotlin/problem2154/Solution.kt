package com.hj.leetcode.kotlin.problem2154

/**
 * LeetCode page: [2154. Keep Multiplying Found Values by Two](https://leetcode.com/problems/keep-multiplying-found-values-by-two/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun findFinalValue(
        nums: IntArray,
        original: Int,
    ): Int {
        var powerMask = 0
        for (num in nums) {
            val bit = lsb(num / original)
            if (original * bit == num) {
                powerMask = powerMask or bit
            }
        }

        return original * lsb(powerMask + 1)
    }

    // Returns the least significant bit of n.
    private fun lsb(n: Int): Int = n and -n
}
