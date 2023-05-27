package com.hj.leetcode.kotlin.problem1406

/**
 * LeetCode page: [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of stoneValue;
     */
    fun stoneGameIII(stoneValue: IntArray): String {
        val (aliceScore, bobScore) = scores(stoneValue)
        return gameResult(aliceScore, bobScore)
    }

    private fun scores(stoneValue: IntArray): List<Int> {
        /* Store the three most recent stoneValue suffix results, initialize with the
         * results of stoneValue[size:], stoneValue[size+1:] and stoneValue[size+2:].
         */
        val suffixResults = ArrayDeque<Int>().apply {
            addLast(0)
            addLast(0)
            addLast(0)
        }

        /* The goal is to compute the result of stoneValue[0:], which is the score of Alice.
         * We solve the result of suffix in an order of decreasing suffix start.
         */
        var suffixSum = 0
        for (suffixStart in stoneValue.lastIndex downTo 0) {
            suffixSum += stoneValue[suffixStart]
            val suffixResult = suffixSum - suffixResults.min()!!
            suffixResults.addFirst(suffixResult)
            suffixResults.removeLast()
        }

        // Return the score of Alice and Bob
        val aliceScore = suffixResults.first()
        val bobScore = suffixSum - aliceScore
        return listOf(aliceScore, bobScore)
    }

    private fun gameResult(aliceScore: Int, bobScore: Int): String = when {
        aliceScore > bobScore -> "Alice"
        aliceScore < bobScore -> "Bob"
        else -> "Tie"
    }
}