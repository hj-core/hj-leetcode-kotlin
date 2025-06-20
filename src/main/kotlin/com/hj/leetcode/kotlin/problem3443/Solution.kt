package com.hj.leetcode.kotlin.problem3443

import kotlin.math.abs

/**
 * LeetCode page: [3443. Maximum Manhattan Distance After K Changes](https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun maxDistance(
        s: String,
        k: Int,
    ): Int {
        var netN = 0
        var netE = 0
        var result = 0

        for ((i, c) in s.withIndex()) {
            when (c) {
                'N' -> netN++
                'S' -> netN--
                'E' -> netE++
                'W' -> netE--
            }
            result = maxOf(result, minOf(i + 1, abs(netN) + abs(netE) + k * 2))
        }
        return result
    }
}
