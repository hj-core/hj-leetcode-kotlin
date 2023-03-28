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
        for (index in days.indices.reversed()) {
            val day = days[index]
            suffixMinCost[index] = minOf(
                costs[0] + suffixMinCost[indexOfFirstGreaterThan(day, days, index)],
                costs[1] + suffixMinCost[indexOfFirstGreaterThan(day + 6, days, index)],
                costs[2] + suffixMinCost[indexOfFirstGreaterThan(day + 29, days, index)]
            )
        }
        return suffixMinCost[0]
    }

    private fun indexOfFirstGreaterThan(target: Int, sorted: IntArray, fromIndex: Int = 0): Int {
        val lessThanMin = target < sorted[fromIndex]
        if (lessThanMin) return fromIndex

        val notLessThanMax = target >= sorted.last()
        if (notLessThanMax) return sorted.size

        var resultIndex = fromIndex
        while (sorted[resultIndex] <= target) {
            resultIndex++
        }
        return resultIndex
    }
}