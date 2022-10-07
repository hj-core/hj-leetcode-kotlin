package com.hj.leetcode.kotlin.problem732

import java.util.*

/**
 * LeetCode page: [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii/);
 *
 * TODO : There is dynamic segment tree solution which improves the time complexity ([see Ref](https://leetcode.com/problems/my-calendar-iii/solution/));
 */
class MyCalendarThree {

    private val changePerTimestamp = containerOfChangeInActiveBookingsPerTimestamp()

    /* Complexity of N calls:
     * Time O(N^2) and Space O(N);
     */
    fun book(start: Int, end: Int): Int {
        require(start < end)

        changePerTimestamp.let {
            it[start] = it.getOrDefault(start, 0) + 1
            it[end] = it.getOrDefault(end, 0) - 1
        }

        var maxActiveBookings = 0
        var currActiveBookings = 0
        val sortedChanges = getChangeInActiveBookingsSortedByTimestamp()

        for (change in sortedChanges) {
            currActiveBookings += change
            maxActiveBookings = maxOf(maxActiveBookings, currActiveBookings)
        }
        return maxActiveBookings
    }

    private fun containerOfChangeInActiveBookingsPerTimestamp(): TreeMap<Int, Int> {
        return TreeMap<Int, Int>()
    }

    private fun getChangeInActiveBookingsSortedByTimestamp(): Collection<Int> {
        check(changePerTimestamp is TreeMap)
        return changePerTimestamp.values
    }
}