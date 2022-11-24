package com.hj.leetcode.kotlin.problem79

/**
 * LeetCode page: [79. Word Search](https://leetcode.com/problems/word-search/);
 */
class Solution {
    /* Complexity:
     * Time O(|board| * |word|) and Space O(|board|);
     */
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val visited = List(board.size) { row -> BooleanArray(board[row].size) }
        for (row in board.indices) {
            for (column in board[row].indices) {
                val hasValidPath = dfs(row, column, 0, word, board, visited)
                if (hasValidPath) return true
            }
        }
        return false
    }

    private fun dfs(
        row: Int, column: Int,
        currWordIndex: Int, word: String,
        board: Array<CharArray>, visited: List<BooleanArray>
    ): Boolean {
        val isInvalidPath =
            isOutOfBoard(row, column, board) || visited[row][column] || board[row][column] != word[currWordIndex]
        if (isInvalidPath) return false

        val hasFoundLastChar = currWordIndex == word.lastIndex
        if (hasFoundLastChar) return true

        visited[row][column] = true
        val neighbours = listOf(
            Pair(row - 1, column), Pair(row, column - 1),
            Pair(row + 1, column), Pair(row, column + 1)
        )
        val hasValidPath = neighbours.any { (nextRow, nextColumn) ->
            dfs(nextRow, nextColumn, currWordIndex + 1, word, board, visited)
        }
        visited[row][column] = false
        return hasValidPath
    }

    private fun isOutOfBoard(row: Int, column: Int, board: Array<CharArray>): Boolean {
        return row !in board.indices || column !in board[row].indices
    }
}