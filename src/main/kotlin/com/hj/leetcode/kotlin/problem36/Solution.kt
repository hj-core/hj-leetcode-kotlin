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
        for (row in board) {
            val visited = BooleanArray(9)
            for (value in row) {
                if (value == '.') continue
                val index = value - '1'
                if (visited[index]) return false else visited[index] = true
            }
        }
        return true
    }

    private fun checkColumns(board: Array<CharArray>): Boolean {
        for (column in board[0].indices) {
            val visited = BooleanArray(9)
            for (row in board.indices) {
                val value = board[row][column]
                if (value == '.') continue
                val index = value - '1'
                if (visited[index]) return false else visited[index] = true
            }
        }
        return true
    }

    private fun checkSquares(board: Array<CharArray>): Boolean {
        for (r0 in board.indices step 3) {
            for (c0 in board[r0].indices step 3) {
                if (!checkSquare(board, r0, c0)) return false
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
        for (row in r0 until r0 + 3) {
            for (column in c0 until c0 + 3) {
                val value = board[row][column]
                if (value == '.') continue
                val index = value - '1'
                if (visited[index]) return false else visited[index] = true
            }
        }
        return true
    }
}
