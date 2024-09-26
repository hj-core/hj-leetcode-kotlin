package com.hj.leetcode.kotlin.problem729

import java.util.*

/**
 * LeetCode page: [729. My Calendar I](https://leetcode.com/problems/my-calendar-i/);
 */
class Solution

class MyCalendar {
    private data class Slot(
        val start: Int,
        val end: Int,
    )

    private val sortedSlots = TreeSet<Slot>(compareBy { it.start })

    /* Complexity for N calls:
     * Time O(NLogN) and Space O(N).
     */
    fun book(
        start: Int,
        end: Int,
    ): Boolean {
        val previous = sortedSlots.lower(Slot(end, end)) ?: Slot(0, 0)
        if (start < previous.end) {
            return false
        }
        return sortedSlots.add(Slot(start, end))
    }
}
