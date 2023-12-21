package com.hj.leetcode.kotlin.problem841

/**
 * LeetCode page: [841. Keys and Rooms](https://leetcode.com/problems/keys-and-rooms/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N) where N and M are the number of rooms and keys;
     */
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val isAccessible = BooleanArray(rooms.size)
        var numAccessible = 0
        val pendingAccess = ArrayDeque<Int>()

        pendingAccess.addLast(0)
        isAccessible[0] = true
        numAccessible++

        while (pendingAccess.isNotEmpty()) {
            val room = pendingAccess.removeFirst()
            val keys = rooms[room]
            for (key in keys) {
                if (isAccessible[key]) continue
                pendingAccess.addLast(key)
                isAccessible[key] = true
                numAccessible++
                if (numAccessible == rooms.size) return true
            }
        }
        return false
    }
}