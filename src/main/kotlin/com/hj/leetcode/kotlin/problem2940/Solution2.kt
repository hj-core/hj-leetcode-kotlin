package com.hj.leetcode.kotlin.problem2940

import java.util.*

/**
 * LeetCode page: [2940. Find Building Where Alice and Bob Can Meet](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N+MLogM) and Space O(M+N)
     * where N and M are the length of heights and queries, respectively.
     */
    fun leftmostBuildingQueries(
        heights: IntArray,
        queries: Array<IntArray>,
    ): IntArray {
        val result = IntArray(queries.size)
        // Answer the easy queries.
        // Meanwhile, group the remaining queries by right, i.e., the larger value of a and b.
        val pendingQueries = List(heights.size) { mutableListOf<PendingQuery>() }
        for ((i, query) in queries.withIndex()) {
            val (a, b) = query
            val (left, right) = if (a <= b) a to b else b to a
            if (left == right || heights[left] < heights[right]) {
                result[i] = right
            } else {
                pendingQueries[right].add(PendingQuery(i, heights[left]))
            }
        }

        // For each index of heights, find the pending queries can be answered by it
        val queryPq = PriorityQueue<PendingQuery>(compareBy { it.height })
        for ((j, height) in heights.withIndex()) {
            while (queryPq.isNotEmpty() && queryPq.peek().height < height) {
                val i = queryPq.poll().index
                result[i] = j
            }
            for (query in pendingQueries[j]) {
                queryPq.offer(query)
            }
        }

        // Handle the remaining queries, whose answer is -1
        for (query in queryPq.asIterable()) {
            result[query.index] = -1
        }
        return result
    }

    private data class PendingQuery(
        val index: Int,
        val height: Int,
    )
}
