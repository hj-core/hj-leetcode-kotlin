package com.hj.leetcode.kotlin.problem773

/**
 * LeetCode page: [773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle/);
 */
class Solution {
    /* Complexity:
     * Time O((M*N)!*(M*N)) and Space O((M*N)!)
     * where M and N are the number of rows and columns in board, respectively.
     */
    fun slidingPuzzle(board: Array<IntArray>): Int {
        require(board.size == 2)
        require(board[0].size == 3 && board[1].size == 3)
        if (outOfOrderPairs(board) % 2 != 0) {
            return -1
        }
        val endingBoard = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 0))
        val root = describeBoard(endingBoard)
        val target = describeBoard(board)
        val bfsQueue = ArrayDeque<Int>()
        val visited = mutableSetOf<Int>()
        var result = 0 // Depth of BFS
        bfsQueue.addLast(root)
        visited.add(root)

        while (bfsQueue.isNotEmpty()) {
            repeat(bfsQueue.size) {
                val node = bfsQueue.removeFirst()
                if (node == target) {
                    return result
                }
                for (neighbour in neighbours(node)) {
                    if (neighbour !in visited) {
                        visited.add(neighbour)
                        bfsQueue.addLast(neighbour)
                    }
                }
            }
            result++
        }
        throw IllegalStateException()
    }

    // See 'Fifteen Puzzle' invariant for reference
    private fun outOfOrderPairs(board: Array<IntArray>): Int {
        val list = board.map { it.filter { v -> v != 0 } }.flatten()
        var result = 0
        for ((i, first) in list.withIndex()) {
            for (j in i + 1..<list.size) {
                if (first > list[j]) {
                    result++
                }
            }
        }
        return result
    }

    // Describe the board using 18 bits.
    // That is, [[b0, b1, b2], [b3, b4, b5]] -> b5_b4_b3_b2_b1_b0
    private fun describeBoard(board: Array<IntArray>): Int {
        var result = 0
        var shift = 0
        for (row in board) {
            for (num in row) {
                result += num shl shift
                shift += 3
            }
        }
        return result
    }

    private fun neighbours(boardDescription: Int): List<Int> {
        val indexEmpty =
            (0..<6).first {
                boardDescription and (0b111 shl (it * 3)) == 0
            }
        val maskEmpty = 0b111 shl (indexEmpty * 3)
        val result = mutableListOf<Int>()
        if (indexEmpty != 0 && indexEmpty != 3) {
            val leftNum = boardDescription and (maskEmpty shr 3)
            result.add(boardDescription - leftNum + (leftNum shl 3))
        }
        if (indexEmpty != 2 && indexEmpty != 5) {
            val rightNum = boardDescription and (maskEmpty shl 3)
            result.add(boardDescription - rightNum + (rightNum shr 3))
        }
        if (indexEmpty < 3) {
            val numBelow = boardDescription and (maskEmpty shl 9)
            result.add(boardDescription - numBelow + (numBelow shr 9))
        } else {
            val numAbove = boardDescription and (maskEmpty shr 9)
            result.add(boardDescription - numAbove + (numAbove shl 9))
        }
        return result
    }
}
