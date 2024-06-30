package com.hj.leetcode.kotlin.problem1208

import kotlin.math.abs
import kotlin.math.max

/**
 * LeetCode page: [1208. Get Equal Substrings Within Budget](https://leetcode.com/problems/get-equal-substrings-within-budget/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun equalSubstring(s: String, t: String, maxCost: Int): Int {
        var result = 0
        var availableCost = maxCost
        var endExclusive = 0
        for (start in s.indices) {
            availableCost += if (start > 0) charCost(start - 1, s, t) else 0
            while (endExclusive < s.length
                && charCost(endExclusive, s, t) <= availableCost
            ) {
                availableCost -= charCost(endExclusive, s, t)
                endExclusive++
            }
            result = max(result, endExclusive - start)
        }
        return result
    }

    private fun charCost(index: Int, s: String, t: String): Int {
        return abs(s[index] - t[index])
    }
}