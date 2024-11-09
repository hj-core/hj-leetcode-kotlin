package com.hj.leetcode.kotlin.problem3133

/**
 * LeetCode page: [3133. Minimum Array End](https://leetcode.com/problems/minimum-array-end/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1).
     */
    fun minEnd(
        n: Int,
        x: Int,
    ): Long {
        // require(1 <= n, x <= 10^8 < 2^27)
        // Imagine a row of 64 lightbulbs.
        // Initially, lightbulbs at positions corresponding to set bits in x are broken.
        // We disregard the broken lightbulbs and use the remaining lightbulbs to represent n-1.
        var result = x.toLong()
        var nextZeroBit = 1L
        while (result and nextZeroBit != 0L) {
            nextZeroBit = nextZeroBit shl 1
        }
        val last = n - 1
        for (shift in 0..<27) {
            val shouldSet = last and (1 shl shift) != 0
            if (shouldSet) {
                result = result or nextZeroBit
            }
            nextZeroBit = nextZeroBit shl 1
            while (nextZeroBit and result != 0L) {
                nextZeroBit = nextZeroBit shl 1
            }
        }
        return result
    }
}
