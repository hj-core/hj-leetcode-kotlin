package com.hj.leetcode.kotlin.problem37

/**
 * LeetCode page: [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/);
 */
class Solution {
    // Complexity:
    // Time O(???) and Space O(???).
    fun solveSudoku(board: Array<CharArray>) {
        val steps = MetaBoard(board).solve()
        for (fill in steps) {
            board[fill.row][fill.col] = fill.digit.digitToChar()
        }
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

    private val empties = mutableListOf<MetaCell>()

    init {
        for (r in rawBoard.indices) {
            for (c in rawBoard[r].indices) {
                if (rawBoard[r][c] == '.') {
                    empties.add(board[r][c])
                } else {
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

    // Returns the steps to solve the board.
    fun solve(): List<Fill> {
        val result = mutableListOf<Fill>()

        while (empties.isNotEmpty()) {
            val cell = popMinNoc(empties)
            val fill = Fill(cell.row(), cell.col(), 1)

            while (fill.digit < 10 && !canApply(fill)) {
                fill.digit++
            }

            // Branch 1: We can find a valid value for the cell
            if (fill.digit < 10) {
                apply(fill)
                result.add(fill)
                continue
            }

            // Branch 2: We cannot find a valid value for the cell
            empties.add(cell)
            while (true) {
                val lastFill = result.last()
                revert(lastFill)

                lastFill.digit++
                while (lastFill.digit < 10 && !canApply(lastFill)) {
                    lastFill.digit++
                }

                if (lastFill.digit < 10) {
                    apply(lastFill)
                    break
                } else {
                    empties.add(board[lastFill.row][lastFill.col])
                    result.removeLast()
                }
            }
        }
        return result
    }

    // Pops the cell with the least number of choices from empties.
    private fun popMinNoc(empties: MutableList<MetaCell>): MetaCell {
        for (i in 1 until empties.size) {
            if (empties[i - 1].noc() < empties[i].noc()) {
                empties[i] = empties[i - 1].also { empties[i - 1] = empties[i] }
            }
        }
        return empties.removeLast()
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
}

// MetaCell uses the following encoding scheme,
//
// zeros |  noc  | freq9 | ..... | freq1 | digit |  col  |  row  |
// ... 52      48      44      16      12       8       4       0
//
// where noc represents the number of choices, and freqX indicates
// the count of X within its row, column and 3x3 square.
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
