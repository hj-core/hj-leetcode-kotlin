package com.hj.leetcode.kotlin.problem909

/**
 * LeetCode page: [909. Snakes and Ladders](https://leetcode.com/problems/snakes-and-ladders/);
 */
class Solution {
    // Complexity:
    // Time O(K*(N^2)) and Space O(N^2) where N is the length of board,
    // and K is the number of possible dice values.
    fun snakesAndLadders(board: Array<IntArray>): Int {
        // BFS
        val n = board.size
        val totalSquares = n * n
        val visited = BooleanArray(totalSquares + 1)
        val queue = OnceQueue(totalSquares)

        var rolls = 0
        queue.pushRight(1)
        visited[1] = true

        while (!queue.isEmpty()) {
            rolls++
            repeat(queue.size()) {
                val curr = queue.popLeft()

                for (dice in 1..6) {
                    var next = curr + dice
                    if (next >= totalSquares) {
                        return rolls
                    }

                    val boardValue = readBoardValue(board, next)
                    if (boardValue != -1) {
                        next = boardValue
                    }

                    if (!visited[next]) {
                        if (next == totalSquares) {
                            return rolls
                        }
                        visited[next] = true
                        queue.pushRight(next)
                    }
                }
            }
        }
        return -1
    }

    private class OnceQueue(
        maxPushes: Int,
    ) {
        private val container = IntArray(maxPushes)
        private var head = 0
        private var tail = 0

        fun size(): Int = tail - head

        fun isEmpty(): Boolean = size() == 0

        fun popLeft(): Int {
            if (isEmpty()) {
                throw NoSuchElementException()
            }
            val result = container[head]
            head++
            return result
        }

        fun pushRight(value: Int) {
            if (tail == container.size) {
                throw IllegalStateException()
            }
            container[tail] = value
            tail++
        }
    }

    private fun readBoardValue(
        board: Array<IntArray>,
        square: Int,
    ): Int {
        val n = board.size
        var row = (square - 1) / n
        var col = (square - 1) % n

        if (row and 1 == 1) {
            col = n - 1 - col
        }
        row = n - 1 - row
        return board[row][col]
    }
}
