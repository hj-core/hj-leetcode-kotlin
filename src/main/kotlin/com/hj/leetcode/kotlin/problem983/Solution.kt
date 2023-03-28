package com.hj.leetcode.kotlin.problem983

/**
 * LeetCode page: [983. Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of days;
     */
    fun mincostTickets(days: IntArray, costs: IntArray): Int {
        // suffixMinCost[i] ::= the min cost of the suffix array of days start from index i
        val suffixMinCost = IntArray(days.size + 1)
        for (i in days.indices.reversed()) {
            // Case 1: We buy a day pass on day[i]
            val today = days[i]
            val dayPassMinCost =
                costs[0] + suffixMinCost[days.firstIndex(fromIndex = i) { day -> day > today }]
            // Case 2: We buy a week pass on day[i]
            val weekPassExpiryDay = today + 6
            val weekPassMinCost =
                costs[1] + suffixMinCost[days.firstIndex(fromIndex = i) { day -> day > weekPassExpiryDay }]
            // Case 3: We buy a month pass on day[i]
            val monthPassExpiryDay = today + 29
            val monthPassMinCost =
                costs[2] + suffixMinCost[days.firstIndex(fromIndex = i) { day -> day > monthPassExpiryDay }]
            // The min cost is the min among possible cases
            suffixMinCost[i] = minOf(dayPassMinCost, weekPassMinCost, monthPassMinCost)
        }
        return suffixMinCost[0]
    }

    private fun IntArray.firstIndex(
        fromIndex: Int = 0,
        fallBackValue: Int = size,
        predicate: (element: Int) -> Boolean = { true }
    ): Int {
        var index = fromIndex
        while (index < size) {
            val element = this[index]
            val isMatched = predicate(element)
            if (isMatched) return index
            index++
        }
        return fallBackValue
    }
}