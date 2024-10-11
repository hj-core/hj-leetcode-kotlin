package com.hj.leetcode.kotlin.problem1942

import java.util.*

/**
 * LeetCode page: [1942. The Number of the Smallest Unoccupied Chair](https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/);
 */
class Solution {
    /* Complexity
     * Time O(NLogN) and Space O(N) where N is the size of times.
     */
    fun smallestChair(
        times: Array<IntArray>,
        targetFriend: Int,
    ): Int {
        val freeSeats = PriorityQueue<Int>() // Existing free seats
        var newSeat = 0 // New seat number if all existing seats are being occupied
        val occupiedSeats =
            PriorityQueue<Pair<Int, Int>>(
                compareBy { (seat, leavingTime) -> leavingTime },
            )

        val friends = times.indices
        val sortedFriends = friends.sortedBy { times[it][0] }

        for (friend in sortedFriends) {
            val (arrivalTime, leavingTime) = times[friend]
            // Some friends may have left, reclaim their seats
            while (occupiedSeats.isNotEmpty() && occupiedSeats.peek().second <= arrivalTime) {
                val freedSeat = occupiedSeats.poll().first
                freeSeats.offer(freedSeat)
            }
            // If all existing seats are being occupied, add a new one
            if (freeSeats.isEmpty()) {
                freeSeats.offer(newSeat)
                newSeat += 1
            }
            // Assign a seat to the arriving friend
            val seat = freeSeats.poll()
            occupiedSeats.offer(Pair(seat, leavingTime))

            if (friend == targetFriend) {
                return seat
            }
        }
        throw IllegalStateException("Execution should not reach here.")
    }
}
