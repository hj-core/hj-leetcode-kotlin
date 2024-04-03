package com.hj.leetcode.kotlin.problem79

/**
 * LeetCode page: [79. Word Search](https://leetcode.com/problems/word-search/);
 */
class Solution {
    /* Complexity:
     * Time O(MN * (3^L)) and Space O(MN) where M and N are the number of
     * rows and columns of board, and L is the length of word;
     */
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val visited = List(board.size) { BooleanArray(board[it].size) }
        for (row in board.indices) {
            for (column in board[row].indices) {
                val exist = dfs(board, word, 0, row, column, visited)
                if (exist) {
                    return true
                }
            }
        }
        return false
    }

    private fun dfs(
        board: Array<CharArray>,
        word: String, progress: Int,
        row: Int, column: Int,
        visited: List<BooleanArray>,
    ): Boolean {
        if (progress == word.length) {
            return true
        }

        if (!inBoard(board, row, column)
            || visited[row][column]
            || board[row][column] != word[progress]
        ) {
            return false
        }

        visited[row][column] = true
        val result = listOf(
            dfs(board, word, progress + 1, row + 1, column, visited),
            dfs(board, word, progress + 1, row - 1, column, visited),
            dfs(board, word, progress + 1, row, column + 1, visited),
            dfs(board, word, progress + 1, row, column - 1, visited),
        ).any { it }
        visited[row][column] = false
        return result
    }

    private fun inBoard(board: Array<CharArray>, row: Int, column: Int): Boolean {
        return row in board.indices && column in board[row].indices
    }
}