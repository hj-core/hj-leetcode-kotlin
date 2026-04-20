package com.hj.leetcode.kotlin.problem2078

/**
 * LeetCode page: [2078. Two Furthest Houses With Different Colors](https://leetcode.com/problems/two-furthest-houses-with-different-colors/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of colors.
    fun maxDistance(colors: IntArray): Int {
        if (colors.first() != colors.last()) {
            return colors.size - 1
        }

        var maxD = 0
        var second = colors.size // the smallest i that colors[i] != colors[0]
        for (i in 1..<colors.size) {
            if (colors[i] != colors[0]) {
                maxD = i
                second = minOf(second, i)
            } else {
                maxD = maxOf(maxD, i - second)
            }
        }

        return maxD
    }
}
