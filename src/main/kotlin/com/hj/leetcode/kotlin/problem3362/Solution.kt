package com.hj.leetcode.kotlin.problem3362

import java.util.*

/**
 * LeetCode page: [3362. Zero Array Transformation III](https://leetcode.com/problems/zero-array-transformation-iii/);
 */
class Solution {
    // Complexity:
    // Time O(N+MLogM) and Space O(N+M) where N and M are the length of nums and queries,
    // respectively.
    fun maxRemoval(
        nums: IntArray,
        queries: Array<IntArray>,
    ): Int {
        var minQueryCount = 0 // The minimum amount of queries for zero transformation
        val sortedQueries = queries.sortedBy { (left, right) -> left }
        var qIndex = 0 // The index to the first unseen query in sortedQueries

        val delta = IntArray(nums.size + 1) // For line sweep
        var reduction = 0 // The accumulated delta for current index

        // A pq contains the right bounds of available queries. Every query has a left
        // bound not later than the current index.
        val queryPq = PriorityQueue<Int>(reverseOrder())

        for ((i, num) in nums.withIndex()) {
            while (qIndex < sortedQueries.size && sortedQueries[qIndex][0] <= i) {
                queryPq.add(sortedQueries[qIndex][1])
                qIndex++
            }

            reduction += delta[i]
            while (reduction < num && queryPq.isNotEmpty() && i <= queryPq.peek()) {
                val right: Int = queryPq.poll()
                reduction++
                delta[right + 1]--
                minQueryCount++
            }

            if (reduction < num) {
                return -1
            }
        }
        return queries.size - minQueryCount
    }
}
