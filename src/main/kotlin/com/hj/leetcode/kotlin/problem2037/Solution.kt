package com.hj.leetcode.kotlin.problem2037

import kotlin.math.abs

/**
 * LeetCode page: [2037. Minimum Number of Moves to Seat Everyone](https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of seats and students;
     */
    fun minMovesToSeat(seats: IntArray, students: IntArray): Int {
        val sortedSeats = seats.sorted()
        val sortedStudents = students.sorted()

        return sortedStudents
            .indices
            .sumOf { abs(sortedSeats[it] - sortedStudents[it]) }
    }
}