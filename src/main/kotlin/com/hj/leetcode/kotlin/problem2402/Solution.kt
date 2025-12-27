package com.hj.leetcode.kotlin.problem2402

import java.util.*

/**
 * LeetCode page: [2402. Meeting Rooms III](https://leetcode.com/problems/meeting-rooms-iii/);
 */
class Solution {
    // Complexity:
    // Time O(MLogM+(n+M)Log(n)) and Space O(n+M) where M is the
    // length of meetings.
    fun mostBooked(
        n: Int,
        meetings: Array<IntArray>,
    ): Int {
        val freeRooms = PriorityQueue<Room>(compareBy(Room::number))
        for (i in 0..<n) {
            freeRooms.add(
                Room(number = i, useUntil = 0L, useCount = 0),
            )
        }

        val usedRooms =
            PriorityQueue<Room>(compareBy(Room::useUntil))

        val sortedMeetings =
            meetings.sortedArrayWith(compareBy { it[0] })

        var minStart = 0L // the earliest start of the next meeting

        for (meeting in sortedMeetings) {
            minStart = maxOf(minStart, meeting[0].toLong())
            if (freeRooms.isEmpty()) {
                minStart =
                    maxOf(minStart, usedRooms.peek().useUntil)
            }

            while (usedRooms.isNotEmpty() &&
                usedRooms.peek().useUntil <= minStart
            ) {
                freeRooms.add(usedRooms.poll())
            }

            val room = freeRooms.poll()
            room.useUntil = minStart + (meeting[1] - meeting[0])
            room.useCount++
            usedRooms.add(room)
        }

        var mostBookedRoom = usedRooms.peek()
        for (rooms in arrayOf(usedRooms, freeRooms)) {
            for (room in rooms.iterator()) {
                if (isMoreBooked(room, mostBookedRoom)) {
                    mostBookedRoom = room
                }
            }
        }

        return mostBookedRoom.number
    }

    private class Room(
        val number: Int,
        var useUntil: Long,
        var useCount: Int,
    )

    private fun isMoreBooked(
        roomA: Room,
        roomB: Room,
    ): Boolean {
        if (roomA.useCount == roomB.useCount) {
            return roomA.number < roomB.number
        }
        return roomA.useCount > roomB.useCount
    }
}
