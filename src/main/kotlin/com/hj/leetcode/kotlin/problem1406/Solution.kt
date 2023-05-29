package com.hj.leetcode.kotlin.problem1406

/**
 * LeetCode page: [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of stoneValue;
     */
    fun stoneGameIII(stoneValue: IntArray): String {
        val (aliceScore, bobScore) = playerScores(stoneValue)
        return gameResult(aliceScore, bobScore)
    }

    private fun playerScores(stoneValue: IntArray): Pair<Int, Int> {
        /* (By dynamic programming)
         * Define the sub problem as the score of the first player when playing game with
         * the suffix arrays of stoneValue.
         *
         * Only the three most recent sub results are stored, which is sufficient to solve
         * the original problem.
         */
        val subProblemResults = ArrayDeque<Int>()

        /* Add base cases that 0 score for suffix arrays stoneValue[size:], stoneValue[size+1:]
         * and stoneValue[size+2:].
         */
        subProblemResults.apply {
            addLast(0)
            addLast(0)
            addLast(0)
        }

        // Solve the sub problem in descending order of the suffix array start index
        var suffixSum = 0
        for (start in stoneValue.lastIndex downTo 0) {
            suffixSum += stoneValue[start]
            val currentResult = suffixSum - subProblemResults.min()!!
            subProblemResults.addFirst(currentResult)
            subProblemResults.removeLast()
        }

        // Compute and return the play scores
        val firstPlayerScore = subProblemResults.first()
        val secondPlayerScore = suffixSum - firstPlayerScore
        return Pair(firstPlayerScore, secondPlayerScore)
    }

    private fun gameResult(aliceScore: Int, bobScore: Int): String = when {
        aliceScore > bobScore -> "Alice"
        aliceScore < bobScore -> "Bob"
        else -> "Tie"
    }
}