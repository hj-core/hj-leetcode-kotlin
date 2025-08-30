package com.hj.leetcode.kotlin.problem36

/**
 * LeetCode page: [36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1) if we treat the 9 by 9 size
    // as a constant.
    fun isValidSudoku(board: Array<CharArray>): Boolean = checkRows(board) && checkColumns(board) && checkSquares(board)

    private fun checkRows(board: Array<CharArray>): Boolean {
        for (r in board.indices) {
            val visited = BooleanArray(9)
            for (c in board[r].indices) {
                if (markVisited(board[r][c], visited)) {
                    return false
                }
            }
        }
        return true
    }

    // Marks the value visited and returns whether it has been
    // visited before.
    private fun markVisited(
        value: Char,
        visited: BooleanArray,
    ): Boolean {
        if (value == '.') {
            return false
        }

        val index = value - '1'
        if (visited[index]) {
            return true
        }

        visited[index] = true
        return false
    }

    private fun checkColumns(board: Array<CharArray>): Boolean {
        for (c in board[0].indices) {
            val visited = BooleanArray(9)
            for (r in board.indices) {
                if (markVisited(board[r][c], visited)) {
                    return false
                }
            }
        }
        return true
    }

    private fun checkSquares(board: Array<CharArray>): Boolean {
        for (r0 in board.indices step 3) {
            for (c0 in board[r0].indices step 3) {
                if (!checkSquare(board, r0, c0)) {
                    return false
                }
            }
        }
        return true
    }

    private fun checkSquare(
        board: Array<CharArray>,
        r0: Int,
        c0: Int,
    ): Boolean {
        val visited = BooleanArray(9)
        for (r in r0 until r0 + 3) {
            for (c in c0 until c0 + 3) {
                if (markVisited(board[r][c], visited)) {
                    return false
                }
            }
        }
        return true
    }
}
