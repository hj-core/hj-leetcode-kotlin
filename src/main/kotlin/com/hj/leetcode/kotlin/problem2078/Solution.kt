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

        val second = colors.indexOfFirst { it != colors[0] }
        if (second == -1) {
            return 0
        }

        var maxD = second
        for (i in second + 1..<colors.size) {
            maxD =
                if (colors[i] != colors[0]) {
                    i
                } else {
                    maxOf(maxD, i - second)
                }
        }

        return maxD
    }
}
