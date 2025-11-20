package com.hj.leetcode.kotlin.problem757

/**
 * LeetCode page: [757. Set Intersection Size At Least Two](https://leetcode.com/problems/set-intersection-size-at-least-two/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of
    // intervals.
    fun intersectionSizeTwo(intervals: Array<IntArray>): Int {
        // The most recent two values of our greedy nums
        val recentTwo = intArrayOf(-2, -1)
        var result = 0
        val sortedIntervals = intervals.sortedBy(IntArray::last)

        for (interval in sortedIntervals) {
            if (recentTwo[1] < interval[0]) {
                recentTwo[0] = interval[1] - 1
                recentTwo[1] = interval[1]
                result += 2
            } else if (recentTwo[0] < interval[0]) {
                recentTwo[0] =
                    minOf(recentTwo[1], interval[1] - 1)
                recentTwo[1] = interval[1]
                result += 1
            }
        }
        return result
    }
}
