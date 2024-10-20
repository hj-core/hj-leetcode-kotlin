package com.hj.leetcode.kotlin.problem2406

import kotlin.math.max

/**
 * LeetCode page: [2406. Divide Intervals Into Minimum Number of Groups](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N+K) and Space O(K) where N is the size of intervals and
     * K is the difference between maximum right and minimum left.
     */
    fun minGroups(intervals: Array<IntArray>): Int {
        val minLeft = intervals.minOf { it[0] }
        val maxRight = intervals.maxOf { it[1] }
        // Change of concurrency at each time instance (shifted by minLeft)
        val lineSweep = IntArray(maxRight - minLeft + 2)
        for ((left, right) in intervals) {
            lineSweep[left - minLeft] += 1
            lineSweep[right - minLeft + 1] -= 1
        }

        var result = 0 // The maximum concurrency
        var concurrency = 0
        for (change in lineSweep) {
            concurrency += change
            result = max(result, concurrency)
        }
        return result
    }
}
