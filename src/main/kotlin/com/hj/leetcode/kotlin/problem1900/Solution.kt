package com.hj.leetcode.kotlin.problem1900

/**
 * LeetCode page: [1900. The Earliest and Latest Rounds Where Players Compete](https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete/);
 */
class Solution {
    // Complexity:
    // Time O((n^4)Log n) and Space O((n^2)Log n).
    fun earliestAndLatest(
        n: Int,
        firstPlayer: Int,
        secondPlayer: Int,
    ): IntArray {
        val start = Stage.new(n, firstPlayer - 1, n - secondPlayer)
        val memoization = mutableMapOf<Stage, IntArray>()
        return dfs(start, memoization)
    }

    // Returns the earliest and latest rounds where the two players
    // compete, starting with the given stage.
    private fun dfs(
        start: Stage,
        memoization: MutableMap<Stage, IntArray>,
    ): IntArray {
        if (start.left == start.right) {
            return intArrayOf(1, 1)
        }
        if (start in memoization) {
            return memoization[start]!!
        }

        val result = intArrayOf(start.n, 0)
        val center = (start.n + 1) / 2 // The one-indexed position of symmetry

        for (nextLeft in 0..start.left) {
            var minNextRight: Int
            var maxNextRight: Int

            if (center > start.right) {
                minNextRight = start.left - nextLeft
                maxNextRight = start.right - nextLeft - 1
            } else {
                minNextRight = start.right - center + start.left - nextLeft
                if (start.n and 1 == 1) {
                    minNextRight++
                }
                maxNextRight = center - nextLeft - 2
            }

            for (nextRight in minNextRight..maxNextRight) {
                val childResult = dfs(Stage.new(center, nextLeft, nextRight), memoization)
                result[0] = minOf(result[0], childResult[0])
                result[1] = maxOf(result[1], childResult[1])
            }
        }

        result[0]++
        result[1]++
        memoization[start] = result
        return result
    }
}

// Stage represents a stage of the tournament with n total
// players. There are left players before the first player,
// and right players after the second player.
//
// Invariant: left is always less than or equal to right.
private data class Stage(
    val n: Int,
    val left: Int,
    val right: Int,
) {
    companion object {
        // Creates a new stage with the given values. It swaps the left and
        // right if necessary to enforce the invariant of the stage. This
        // will not affect the final result due to symmetry.
        fun new(
            n: Int,
            left: Int,
            right: Int,
        ): Stage {
            if (left > right) {
                return Stage(n, right, left)
            }
            return Stage(n, left, right)
        }
    }
}
