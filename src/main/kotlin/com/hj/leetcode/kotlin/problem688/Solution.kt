package com.hj.leetcode.kotlin.problem688

/**
 * LeetCode page: [688. Knight Probability in Chessboard](https://leetcode.com/problems/knight-probability-in-chessboard/);
 */
class Solution {
    /* Complexity:
     * Time O(k * n^2) and Space O(k * n^2);
     */
    fun knightProbability(n: Int, k: Int, row: Int, column: Int): Double {
        return knightProbability(n, KnightState(row, column, k))
    }

    private fun knightProbability(
        boardSize: Int,
        state: KnightState,
        cachedResults: MutableMap<KnightState, Double> = hashMapOf()
    ): Double {
        if (state in cachedResults) {
            return checkNotNull(cachedResults[state])
        }
        if (state.hasMovedOff(boardSize)) {
            return 0.0
        }
        if (state.remainingMoves == 0) {
            return 1.0
        }

        var result = 0.0
        for (nextState in state.possibleNextStates()) {
            result += 0.125 * knightProbability(boardSize, nextState, cachedResults)
        }
        cachedResults[state] = result
        return result
    }

    private data class KnightState(val row: Int, val column: Int, val remainingMoves: Int) {

        fun hasMovedOff(boardSize: Int): Boolean {
            return (row < 0 || row >= boardSize) || (column < 0 || column >= boardSize)
        }

        fun possibleNextStates(): List<KnightState> {
            return listOf(
                KnightState(row - 2, column + 1, remainingMoves - 1),
                KnightState(row - 1, column + 2, remainingMoves - 1),
                KnightState(row + 1, column + 2, remainingMoves - 1),
                KnightState(row + 2, column + 1, remainingMoves - 1),
                KnightState(row + 2, column - 1, remainingMoves - 1),
                KnightState(row + 1, column - 2, remainingMoves - 1),
                KnightState(row - 1, column - 2, remainingMoves - 1),
                KnightState(row - 2, column - 1, remainingMoves - 1)
            )
        }
    }
}