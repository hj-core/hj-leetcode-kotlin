package com.hj.leetcode.kotlin.problem37

/**
 * LeetCode page: [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/);
 */
class Solution2 {
    // Complexity:
    // Time O(???) and Space O(???).
    fun solveSudoku(board: Array<CharArray>) {
        val steps = MetaBoard2(board).solve()
        for (fill in steps) {
            val (r, c) = fill.cell
            board[r][c] = fill.digit.digitToChar()
        }
    }
}

private class MetaBoard2(
    board: Array<CharArray>,
) {
    private val rowUsed = IntArray(9)
    private val colUsed = IntArray(9)
    private val boxUsed = Array(3) { IntArray(3) }
    private val empties = mutableListOf<IntArray>()

    init {
        for (r in board.indices) {
            for (c in board[r].indices) {
                val cell = intArrayOf(r, c)
                if (board[r][c] == '.') {
                    empties.add(cell)
                } else {
                    val digit = board[r][c].digitToInt()
                    apply(Fill2(cell, digit))
                }
            }
        }
    }

    // Returns the steps to solve the board.
    fun solve(): List<Fill2> {
        val result = mutableListOf<Fill2>()

        while (empties.isNotEmpty()) {
            val cell = popMinNoc()
            val minDigit = minFillDigit(cell)

            // Branch 1: We can find a valid value for the cell
            if (minDigit < 10) {
                val fill = Fill2(cell, minDigit)
                apply(fill)
                result.add(fill)
                continue
            }

            // Branch 2: We cannot find a valid value for the cell
            empties.add(cell)
            while (true) {
                val lastFill = result.last()
                revert(lastFill)

                val used = usedDigits(lastFill.cell)
                lastFill.digit++
                while (lastFill.digit < 10 && (used and (1 shl lastFill.digit)) != 0) {
                    lastFill.digit++
                }

                if (lastFill.digit < 10) {
                    apply(lastFill)
                    break
                } else {
                    empties.add(lastFill.cell)
                    result.removeLast()
                }
            }
        }
        return result
    }

    // Pops the cell with the least number of choices from empties.
    private fun popMinNoc(): IntArray {
        for (i in 1..<empties.size) {
            if (noc(empties[i - 1]) < noc(empties[i])) {
                empties[i] = empties[i - 1].also { empties[i - 1] = empties[i] }
            }
        }
        return empties.removeLast()
    }

    fun minFillDigit(cell: IntArray): Int {
        val used = usedDigits(cell)
        for (digit in 1..<10) {
            if ((used and (1 shl digit)) == 0) {
                return digit
            }
        }
        return 10
    }

    fun usedDigits(cell: IntArray): Int {
        val (row, col) = cell
        return rowUsed[row] or colUsed[col] or boxUsed[row / 3][col / 3]
    }

    // Returns the current number of choices for this cell.
    fun noc(cell: IntArray): Int {
        val used = usedDigits(cell)
        var result = 0
        for (digit in 1..<10) {
            if ((used and (1 shl digit)) == 0) {
                result++
            }
        }
        return result
    }

    fun apply(fill: Fill2) {
        val (row, col) = fill.cell
        val bit = 1 shl fill.digit
        rowUsed[row] = rowUsed[row] or bit
        colUsed[col] = colUsed[col] or bit
        boxUsed[row / 3][col / 3] = boxUsed[row / 3][col / 3] or bit
    }

    fun revert(fill: Fill2) {
        val (row, col) = fill.cell
        val mask = (1 shl fill.digit).inv()
        rowUsed[row] = rowUsed[row] and mask
        colUsed[col] = colUsed[col] and mask
        boxUsed[row / 3][col / 3] = boxUsed[row / 3][col / 3] and mask
    }
}

private class Fill2(
    val cell: IntArray,
    var digit: Int,
)
