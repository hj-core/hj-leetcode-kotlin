package com.hj.leetcode.kotlin.problem2551

/**
 * LeetCode page: [2551. Put Marbles in Bags](https://leetcode.com/problems/put-marbles-in-bags/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of weights.
    fun putMarbles(
        weights: IntArray,
        k: Int,
    ): Long {
        // let weights = [w0, w1, w2, ..., w_n-1], and splitScores(i)::= w_i + w_i+1
        val splitScores = IntArray(weights.size - 1)
        for (i in 0..<weights.lastIndex) {
            splitScores[i] = weights[i] + weights[i + 1]
        }

        // Denote the length of the splitScores as m. After sorting splitScores,
        //
        // To achieve the minimum scores, we choose the first k-1 splits of splitScores,
        // i.e., splitScores[0..<k-1].
        //
        // To achieve the maximum scores, we choose the last k-1 splits of splitScores,
        // i.e., splitScores[m-k+1..<m].

        splitScores.sort()
        val netSize = minOf(k - 1, splitScores.size - k + 1)
        val netMinScores = (0..<netSize).fold(0L) { acc, i -> acc + splitScores[i] }
        val netMaxScores = ((splitScores.size - netSize)..<splitScores.size).fold(0L) { acc, i -> acc + splitScores[i] }

        return netMaxScores - netMinScores
    }
}
