package com.hj.leetcode.kotlin.problem926

/**
 * LeetCode page: [926. Flip String to Monotone Increasing](https://leetcode.com/problems/flip-string-to-monotone-increasing/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|) and Space O(1);
     */
    fun minFlipsMonoIncr(s: String): Int {
        val totalZeros = s.count { it == '0' }
        var numOnesBeforeIndex = 0
        var minFlips = totalZeros

        for (index in 1..s.length) {
            if (s[index - 1] == '1') numOnesBeforeIndex++
            val numZerosFromIndex = totalZeros - (index - numOnesBeforeIndex)
            val numFlipsSetSeparationAtIndex = numOnesBeforeIndex + numZerosFromIndex
            minFlips = minOf(minFlips, numFlipsSetSeparationAtIndex)
        }
        return minFlips
    }
}