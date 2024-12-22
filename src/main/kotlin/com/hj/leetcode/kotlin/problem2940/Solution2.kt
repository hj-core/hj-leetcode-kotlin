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
        val pendingQueries =
            answerEasyQueriesAndGroupTheRemaining(result, heights, queries)

        answerPendingQueries(result, pendingQueries, heights)
        return result
    }

    private fun answerEasyQueriesAndGroupTheRemaining(
        answerSheet: IntArray,
        heights: IntArray,
        queries: Array<IntArray>,
    ): List<List<PendingQuery>> {
        // Group the remaining queries by their right, i.e., the larger value of a and b
        val result = List(heights.size) { mutableListOf<PendingQuery>() }

        for ((i, query) in queries.withIndex()) {
            val (left, right) = query.min() to query.max()
            if (left == right || heights[left] < heights[right]) {
                answerSheet[i] = right
            } else {
                result[right].add(PendingQuery(i, heights[left]))
            }
        }
        return result
    }

    private data class PendingQuery(
        val index: Int,
        val height: Int,
    )

    private fun answerPendingQueries(
        answerSheet: IntArray,
        pendingQueries: List<List<PendingQuery>>,
        heights: IntArray,
    ) {
        // Stores the pending queries before the current index
        val queryPq = PriorityQueue<PendingQuery>(compareBy { it.height })

        // For each index of heights, answer the queries it is capable
        for ((j, height) in heights.withIndex()) {
            while (queryPq.isNotEmpty() && queryPq.peek().height < height) {
                val i = queryPq.poll().index
                answerSheet[i] = j
            }

            for (query in pendingQueries[j]) {
                queryPq.offer(query)
            }
        }

        // The remaining queries don't have an answer, give them -1
        for (query in queryPq.asIterable()) {
            answerSheet[query.index] = -1
        }
    }
}
