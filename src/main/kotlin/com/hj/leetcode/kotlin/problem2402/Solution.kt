package com.hj.leetcode.kotlin.problem2402

import java.util.*

/**
 * LeetCode page: [2402. Meeting Rooms III](https://leetcode.com/problems/meeting-rooms-iii/);
 */
class Solution {
    // Complexity:
    // Time O(nLog(n)+MLogM+MLog(n)) and Space O(n+M) where M is the length
    // of meetings.
    fun mostBooked(
        n: Int,
        meetings: Array<IntArray>,
    ): Int {
        val freeRooms = PriorityQueue<Int>()
        for (i in 0..<n) {
            freeRooms.add(i)
        }

        val usedRooms = PriorityQueue<Pair<Int, Long>>(compareBy { (room, end) -> end })
        val bookCount = IntArray(n)
        var newStart = 0L
        val sortedMeetings = meetings.sortedBy { (start, _) -> start }

        for (meeting in sortedMeetings) {
            newStart = maxOf(newStart, meeting[0].toLong())
            if (freeRooms.isEmpty()) {
                newStart = maxOf(newStart, checkNotNull(usedRooms.peek()).second)
            }

            while (usedRooms.isNotEmpty() && checkNotNull(usedRooms.peek()).second <= newStart) {
                val room = checkNotNull(usedRooms.poll()).first
                freeRooms.add(room)
            }

            val room = checkNotNull(freeRooms.poll())
            val end = newStart + meeting[1] - meeting[0]
            usedRooms.add(Pair(room, end))
            bookCount[room]++
        }
        return bookCount.withIndex().maxBy { it.value }.index
    }
}
