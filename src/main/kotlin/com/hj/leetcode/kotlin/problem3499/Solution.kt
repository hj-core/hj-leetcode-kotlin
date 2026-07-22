package com.hj.leetcode.kotlin.problem3499

/**
 * LeetCode page: [3499. Maximize Active Section with Trade I](https://leetcode.com/problems/maximize-active-section-with-trade-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun maxActiveSectionsAfterTrade(s: String): Int {
        var rawOneCnt = 0
        var maxTradeGain = 0
        var zeroCnt1 = 0
        var zeroCnt2 = 0
        var prevSection = '1'
        // Find the "001100" pattern substring that contains the maximum number of 0s
        for (c in s) {
            if (c == '0') {
                if (prevSection == '1') {
                    zeroCnt1 = zeroCnt2
                    zeroCnt2 = 1
                } else {
                    zeroCnt2++
                }

                if (zeroCnt1 > 0) {
                    maxTradeGain = maxOf(maxTradeGain, zeroCnt1 + zeroCnt2)
                }
            } else {
                rawOneCnt++
            }

            prevSection = c
        }

        return rawOneCnt + maxTradeGain
    }
}
