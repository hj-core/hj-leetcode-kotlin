package com.hj.leetcode.kotlin.problem36

/**
 * LeetCode page: [36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku/);
 */
class Solution {
    /* Complexity:
     * Time O(|board|) and Space O(1);
     */
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        return checkRows(board) && checkColumns(board) && checkSubBoxes(board)
    }

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

    private fun checkSubBoxes(board: Array<CharArray>): Boolean {
        for (row in board.indices step 3) {
            for (column in board[row].indices step 3) {
                val isValidSubBox = checkSubBox(board, row, column)
                if (!isValidSubBox) return false
            }
        }
        return true
    }

    private fun checkSubBox(board: Array<CharArray>, rowTopLeft: Int, columnTopLeft: Int): Boolean {
        val visited = BooleanArray(9)
        for (row in rowTopLeft until rowTopLeft + 3) {
            for (column in columnTopLeft until columnTopLeft + 3) {
                val value = board[row][column]
                if (value == '.') continue
                val index = value - '1'
                if (visited[index]) return false else visited[index] = true
            }
        }
        return true
    }
}