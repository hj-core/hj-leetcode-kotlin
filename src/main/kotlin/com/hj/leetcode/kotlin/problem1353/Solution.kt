package com.hj.leetcode.kotlin.problem1353

import java.util.*

/**
 * LeetCode page: [1353. Maximum Number of Events That Can Be Attended](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of events.
    fun maxEvents(events: Array<IntArray>): Int {
        val sortedEvents = events.sortedBy { it[0] }

        val available = PriorityQueue<Int>()
        var day = 0
        var iEvent = 0
        var result = 0

        while (iEvent < sortedEvents.size || available.isNotEmpty()) {
            while (available.peek()?.let { it < day } == true) {
                available.poll()
            }
            if (available.isEmpty() && iEvent < sortedEvents.size) {
                day = sortedEvents[iEvent][0]
            }

            while (iEvent < sortedEvents.size && sortedEvents[iEvent][0] <= day) {
                available.add(sortedEvents[iEvent][1])
                iEvent++
            }

            if (available.isEmpty()) {
                break
            }
            available.poll()
            day++
            result++
        }
        return result
    }
}
