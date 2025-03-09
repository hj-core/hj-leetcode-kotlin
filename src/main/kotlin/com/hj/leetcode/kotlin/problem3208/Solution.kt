package com.hj.leetcode.kotlin.problem3208

/**
 * LeetCode page: [3208. Alternating Groups II](https://leetcode.com/problems/alternating-groups-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of colors.
    fun numberOfAlternatingGroups(
        colors: IntArray,
        k: Int,
    ): Int {
        // Extend the `colors` with its first k-1 elements,
        // then partition the extended array into the longest alternating groups,
        // and compute the final result by summing the contribution from each group.
        var result = 0
        val extendedLen = colors.size + k - 1
        var left = 0
        var right = 0
        while (left < colors.size) {
            while (right < extendedLen - 1 && colors[right % colors.size] != colors[(right + 1) % colors.size]) {
                right++
            }
            result += maxOf(0, right - left - k + 2)
            right++
            left = right
        }
        return result
    }
}
