package com.hj.leetcode.kotlin.problem983

/**
 * LeetCode page: [983. Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of days;
     */
    fun mincostTickets(days: IntArray, costs: IntArray): Int {
        // suffixMinCost[i] ::= the min cost of the suffix array of days start from index i
        val suffixMinCost = IntArray(days.size + 1)

        // The last index of days that the pass is still valid
        var weekPassExpiryDayIndex = days.lastIndex
        var monthPassExpiryDayIndex = days.lastIndex

        for (i in days.indices.reversed()) {
            // Case 1: We buy a day pass on day[i]
            val firstDay = days[i]
            val dayPassFirstInvalidIndex = i + 1
            val dayPassMinCost = costs[0] + suffixMinCost[dayPassFirstInvalidIndex]
            // Case 2: We buy a week pass on day[i]
            val weekPassExpiryDay = firstDay + 6
            weekPassExpiryDayIndex = days.lastIndex(upperIndex = weekPassExpiryDayIndex) { day ->
                day <= weekPassExpiryDay
            }
            val weekPassFirstInvalidIndex = weekPassExpiryDayIndex + 1
            val weekPassMinCost = costs[1] + suffixMinCost[weekPassFirstInvalidIndex]
            // Case 3: We buy a month pass on day[i]
            val monthPassExpiryDay = firstDay + 29
            monthPassExpiryDayIndex = days.lastIndex(upperIndex = monthPassExpiryDayIndex) { day ->
                day <= monthPassExpiryDay
            }
            val monthPassFirstInvalidIndex = monthPassExpiryDayIndex + 1
            val monthPassMinCost = costs[2] + suffixMinCost[monthPassFirstInvalidIndex]
            // The min cost is the min among possible cases
            suffixMinCost[i] = minOf(dayPassMinCost, weekPassMinCost, monthPassMinCost)
        }
        return suffixMinCost[0]
    }

    private fun IntArray.lastIndex(
        upperIndex: Int = lastIndex,
        fallBackValue: Int = -1,
        predicate: (element: Int) -> Boolean = { true }
    ): Int {
        var index = upperIndex
        while (index >= 0) {
            val element = this[index]
            val isMatched = predicate(element)
            if (isMatched) return index
            index--
        }
        return fallBackValue
    }
}