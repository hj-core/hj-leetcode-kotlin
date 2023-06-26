package com.hj.leetcode.kotlin.problem2462

import java.util.*

/**
 * LeetCode page: [2462. Total Cost to Hire K Workers](https://leetcode.com/problems/total-cost-to-hire-k-workers/);
 */
class Solution {
    /* Complexity:
     * Time O((k+candidates) * Log(candidates)) and Space O(candidates);
     */
    fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
        if (k == costs.size) {
            return costOfAllWorkers(costs)
        }

        if (costs.size <= candidates * 2) {
            return costOfKLowestWorkers(costs, k)
        }

        val candidatePriority = compareBy<Candidate>({ it.cost }, { it.index })
        val candidatePq = PriorityQueue(candidatePriority).apply {
            addFirstBatch(this, costs, candidates)
        }
        var nextCandidateLeft = Candidate(costs, candidates)
        var nextCandidateRight = Candidate(costs, costs.size - candidates - 1)
        var result = 0L

        repeat(k) {
            val bestCandidate = candidatePq.poll()
            result += bestCandidate.cost

            if (nextCandidateLeft.index <= nextCandidateRight.index) {
                when {
                    bestCandidate.index < nextCandidateLeft.index -> {
                        candidatePq.offer(nextCandidateLeft)
                        nextCandidateLeft = Candidate(costs, nextCandidateLeft.index + 1)
                    }

                    nextCandidateRight.index < bestCandidate.index -> {
                        candidatePq.offer(nextCandidateRight)
                        nextCandidateRight = Candidate(costs, nextCandidateRight.index - 1)
                    }
                }
            }
        }
        return result
    }

    private fun addFirstBatch(
        destination: PriorityQueue<Candidate>,
        costs: IntArray,
        candidates: Int
    ) {
        for (index in 0 until candidates) {
            destination.offer(Candidate(costs[index], index))
        }
        for (index in (costs.size - candidates) until costs.size) {
            destination.offer(Candidate(costs[index], index))
        }
    }

    private fun costOfAllWorkers(costs: IntArray): Long = costs.fold(0L) { acc, i -> acc + i }

    private fun costOfKLowestWorkers(costs: IntArray, k: Int): Long {
        val costPq = PriorityQueue<Int>(reverseOrder())
        for (cost in costs) {
            costPq.offer(cost)
            if (costPq.size > k) {
                costPq.poll()
            }
        }
        return costPq.fold(0L) { acc, i -> acc + i }
    }

    private data class Candidate(val cost: Int, val index: Int) {

        constructor(costs: IntArray, index: Int): this(costs[index], index)
    }
}