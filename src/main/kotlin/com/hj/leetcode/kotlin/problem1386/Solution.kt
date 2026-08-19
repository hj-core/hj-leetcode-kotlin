package com.hj.leetcode.kotlin.problem1386

/**
 * LeetCode page: [1386. Cinema Seat Allocation](https://leetcode.com/problems/cinema-seat-allocation/);
 */
class Solution {
    // Complexity:
    // Time O(M) and space O(M) where M is the length of reservedSeats.
    fun maxNumberOfFamilies(
        n: Int,
        reservedSeats: Array<IntArray>,
    ): Int {
        val allocation =
            reservedSeats
                .groupingBy { it[0] }
                .fold(0) { acc, (_, pos) -> acc or (1 shl pos) }

        return (n - allocation.size) * 2 + allocation.values.sumOf(::availableFourGroup)
    }

    private fun availableFourGroup(rowAllocation: Int): Int =
        when {
            rowAllocation and 0b0_1111_1111_00 == 0 -> 2
            rowAllocation and 0b0_0000_1111_00 == 0 -> 1
            rowAllocation and 0b0_1111_0000_00 == 0 -> 1
            rowAllocation and 0b0_0011_1100_00 == 0 -> 1
            else -> 0
        }
}
