package com.hj.leetcode.kotlin.problem37

/**
 * LeetCode page: [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/);
 */
class Solution {
    // Complexity:
    // Time O(???) and Space O(???).
    fun solveSudoku(board: Array<CharArray>) {
        val metaBoard = MetaBoard(board)
        val empties = metaBoard.collectEmpties()
        val history = mutableListOf<Fill>()

        while (empties.isNotEmpty()) {
            val cell = swapPopMinNoc(empties)

            val newFill = Fill(cell.row(), cell.col(), 1)
            while (newFill.digit < 10 && !metaBoard.canApply(newFill)) {
                newFill.digit++
            }

            // Case 1: We can find a valid value for the cell
            if (newFill.digit < 10) {
                metaBoard.apply(newFill)
                history.add(newFill)
                continue
            }

            // Case 2: We cannot find a valid value for the cell
            empties.add(cell)
            while (true) {
                val lastFill = history.last()
                metaBoard.revert(lastFill)

                lastFill.digit++
                while (lastFill.digit < 10 && !metaBoard.canApply(lastFill)) {
                    lastFill.digit++
                }

                if (lastFill.digit < 10) {
                    metaBoard.apply(lastFill)
                    break
                } else {
                    val cell = metaBoard.getCell(lastFill.row, lastFill.col)
                    empties.add(cell)
                    history.removeLast()
                }
            }
        }

        for (fill in history) {
            board[fill.row][fill.col] = fill.digit.digitToChar()
        }
    }

    private fun swapPopMinNoc(empties: MutableList<MetaCell>): MetaCell {
        for (i in 1 until empties.size) {
            if (empties[i - 1].noc() < empties[i].noc()) {
                empties[i] = empties[i - 1].also { empties[i - 1] = empties[i] }
            }
        }
        return empties.removeLast()
    }
}

private class MetaBoard(
    rawBoard: Array<CharArray>,
) {
    private val board =
        Array(rawBoard.size) { r ->
            Array(rawBoard[r].size) { c ->
                MetaCell.newEmpty(r, c)
            }
        }

    init {
        for (r in rawBoard.indices) {
            for (c in rawBoard[r].indices) {
                if (rawBoard[r][c] != '.') {
                    val digit = rawBoard[r][c].digitToInt()
                    board[r][c].setDigit(digit)
                    onEachSibling(r, c) { sRow, sCol ->
                        board[sRow][sCol].incFreq(digit)
                    }
                }
            }
        }
    }

    private fun onEachSibling(
        row: Int,
        col: Int,
        action: (sRow: Int, sCol: Int) -> Unit,
    ) {
        for (sRow in 0 until 9) {
            if (sRow != row) {
                action(sRow, col)
            }
        }

        for (sCol in 0 until 9) {
            if (sCol != col) {
                action(row, sCol)
            }
        }

        val row0 = row / 3 * 3
        val col0 = col / 3 * 3
        for (sRow in row0 until row0 + 3) {
            for (sCol in col0 until col0 + 3) {
                if (sRow != row && sCol != col) {
                    action(sRow, sCol)
                }
            }
        }
    }

    fun collectEmpties(): MutableList<MetaCell> {
        val result = mutableListOf<MetaCell>()
        for (r in board.indices) {
            for (c in board[r].indices) {
                if (board[r][c].digit() == 0) {
                    result.add(board[r][c])
                }
            }
        }
        return result
    }

    fun canApply(fill: Fill): Boolean {
        val cell = board[fill.row][fill.col]
        return cell.digit() == 0 && cell.freq(fill.digit) == 0
    }

    fun apply(fill: Fill) {
        board[fill.row][fill.col].setDigit(fill.digit)
        onEachSibling(fill.row, fill.col) { sRow, sCol ->
            board[sRow][sCol].incFreq(fill.digit)
        }
    }

    fun revert(fill: Fill) {
        board[fill.row][fill.col].clearDigit()
        onEachSibling(fill.row, fill.col) { sRow, sCol ->
            board[sRow][sCol].decFreq(fill.digit)
        }
    }

    fun getCell(
        row: Int,
        col: Int,
    ): MetaCell = board[row][col]
}

// metaCell has the following encoding scheme,
//
// zeros |  noc  | freq9 | ..... | freq1 | digit |  col  |  row  |
// ... 52      48      44      16      12       8       4       0
//
// where noc means the number of choices, and freqX means
// the number of X from the same row, column and square.
private class MetaCell(
    private var data: Long,
) {
    fun row(): Int = data.toInt() and 0xf

    fun col(): Int = (data shr 4).toInt() and 0xf

    fun digit(): Int = (data shr 8).toInt() and 0xf

    fun setDigit(digit: Int) {
        clearDigit()
        data = data xor (digit.toLong() shl 8)
        incFreq(digit)
    }

    fun clearDigit() {
        val oldDigit = digit()
        if (oldDigit == 0) {
            return
        }
        val mask = 0x000f_ffff_ffff_f0ff
        data = data and mask
        decFreq(oldDigit)
    }

    fun freq(digit: Int): Int = (data shr (8 + digit * 4)).toInt() and 0xf

    fun incFreq(digit: Int) {
        data += 1L shl (8 + digit * 4)
        if (freq(digit) == 1) {
            decNoc()
        }
    }

    fun decFreq(digit: Int) {
        data -= 1L shl (8 + digit * 4)
        if (freq(digit) == 0) {
            incNoc()
        }
    }

    fun noc(): Int = (data shr 48).toInt() and 0xf

    fun incNoc() {
        data += 1L shl 48
    }

    fun decNoc() {
        data -= 1L shl 48
    }

    companion object {
        fun newEmpty(
            row: Int,
            col: Int,
        ): MetaCell {
            var data = 0L
            data = data xor row.toLong()
            data = data xor (col.toLong() shl 4)
            data = data xor (9L shl 48)
            return MetaCell(data)
        }
    }
}

private class Fill(
    val row: Int,
    val col: Int,
    var digit: Int,
)
