package com.hj.leetcode.kotlin.problem1406

/**
 * LeetCode page: [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of stoneValue;
     */
    fun stoneGameIII(stoneValue: IntArray): String {
        val suffixResults = ArrayDeque<Int>().apply {
            addLast(0)
            addLast(0)
            addLast(0)
        }
        var suffixSum = 0
        for (suffixStart in stoneValue.lastIndex downTo 0) {
            suffixSum += stoneValue[suffixStart]
            val suffixResult = suffixSum - suffixResults.min()!!
            suffixResults.addFirst(suffixResult)
            suffixResults.removeLast()
        }

        val aliceScore = suffixResults.first()
        val bobScore = suffixSum - aliceScore
        return gameResult(aliceScore, bobScore)
    }

    private fun gameResult(aliceScore: Int, bobScore: Int): String = when {
        aliceScore > bobScore -> "Alice"
        aliceScore < bobScore -> "Bob"
        else -> "Tie"
    }
}