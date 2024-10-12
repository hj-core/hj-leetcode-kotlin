package com.hj.leetcode.kotlin.problem2406

import kotlin.math.max

/**
 * LeetCode page: [2406. Divide Intervals Into Minimum Number of Groups](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N+K) and Space O(K) where N is the size of intervals and
     * K is the maximum right among intervals.
     */
    fun minGroups(intervals: Array<IntArray>): Int {
        val maxRight = intervals.maxOf { it[1] }
        val lineSweep = IntArray(maxRight + 2)
        for ((left, right) in intervals) {
            lineSweep[left] += 1
            lineSweep[right + 1] -= 1
        }

        var result = 0 // The maximum concurrency
        var concurrency = 0
        for (count in lineSweep) {
            concurrency += count
            result = max(result, concurrency)
        }
        return result
    }
}
