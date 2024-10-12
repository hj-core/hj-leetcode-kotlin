package com.hj.leetcode.kotlin.problem2406

import java.util.*

/**
 * LeetCode page: [2406. Divide Intervals Into Minimum Number of Groups](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of intervals.
     */
    fun minGroups(intervals: Array<IntArray>): Int {
        val groupRights = PriorityQueue<Int>() // Latest rights from each group
        val sortedIntervals = intervals.sortedBy { it[0] }

        for ((left, right) in sortedIntervals) {
            val canAppend = groupRights.isNotEmpty() && groupRights.peek() < left
            if (canAppend) {
                // Append to group with the smallest latest right.
                // Which group to append doesn't matter as long as it is valid because
                // the next interval will have a left not less than the current one.
                groupRights.poll()
                groupRights.offer(right)
            } else {
                groupRights.offer(right)
            }
        }
        return groupRights.size
    }
}
