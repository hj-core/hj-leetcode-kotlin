package com.hj.leetcode.kotlin.problem1845

import java.util.*

/**
 * LeetCode page: [1845. Seat Reservation Manager](https://leetcode.com/problems/seat-reservation-manager/);
 */
class SeatManager(n: Int) {

    private var numReserved = 0
    private val unreserved = TreeSet<Int>()

    fun reserve(): Int {
        numReserved++
        return unreserved.pollFirst() ?: numReserved
    }

    fun unreserve(seatNumber: Int) {
        unreserved.add(seatNumber)
        numReserved--
    }
}
