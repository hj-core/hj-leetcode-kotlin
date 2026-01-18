package com.hj.leetcode.kotlin.problem1895

/**
 * LeetCode page: [1895. Largest Magic Square](https://leetcode.com/problems/largest-magic-square/);
 */
class Solution {
    // Complexity:
    // Time O(MN*min(M,N)^2) and Space O(MN), where M and N are the
    // number of rows and columns of grid, respectively.
    fun largestMagicSquare(grid: Array<IntArray>): Int {
        val suffixSums = SuffixSums(grid)
        val m = grid.size
        val n = grid[0].size

        var maxSize = 1
        var r = 0
        while (r + maxSize < m) {
            var c = 0
            while (c + maxSize < n) {
                var size = minOf(m - r, n - c)
                while (size > maxSize &&
                    !isMagicSquare(r, c, size, suffixSums)
                ) {
                    size--
                }

                maxSize = size
                c++
            }

            r++
        }

        return maxSize
    }

    private class SuffixSums(
        grid: Array<IntArray>,
    ) {
        private val m = grid.size
        private val n = grid[0].size

        // rowSuffix[r][c]:= sum(grid[r][c..])
        private val rowSuffix = Array(m) { IntArray(n + 1) }

        // colSuffix[c][r]:= sum(grid[r..][c])
        private val colSuffix = Array(n) { IntArray(m + 1) }

        // dg1Suffix[r][c]:= sum(grid[r][c], grid[r+1][c+1], ...)
        private val dg1Suffix = Array(m + 1) { IntArray(n + 1) }

        // dg2Suffix[r][c]:= sum(grid[r][c], grid[r+1][c-1], ...)
        private val dg2Suffix = Array(m + 1) { IntArray(n) }

        init {
            for (r in m - 1 downTo 0) {
                for (c in n - 1 downTo 0) {
                    val v = grid[r][c]
                    rowSuffix[r][c] = v + rowSuffix[r][c + 1]
                    colSuffix[c][r] = v + colSuffix[c][r + 1]
                    dg1Suffix[r][c] = v + dg1Suffix[r + 1][c + 1]
                    dg2Suffix[r][c] =
                        if (c > 0) {
                            v + dg2Suffix[r + 1][c - 1]
                        } else {
                            v
                        }
                }
            }
        }

        // Returns the sum of grid[r][cStart..<cEnd].
        fun rowSum(
            r: Int,
            cStart: Int,
            cEnd: Int,
        ): Int = rowSuffix[r][cStart] - rowSuffix[r][cEnd]

        // Returns the sum of grid[rStart..<rEnd][c].
        fun columnSum(
            c: Int,
            rStart: Int,
            rEnd: Int,
        ): Int = colSuffix[c][rStart] - colSuffix[c][rEnd]

        // Returns the sum of the forward diagonal start from (r, c) and
        // length long.
        fun dg1Sum(
            r: Int,
            c: Int,
            length: Int,
        ): Int = dg1Suffix[r][c] - dg1Suffix[r + length][c + length]

        // Returns the sum of the backward diagonal start from (r, c)
        // and length long.
        fun dg2Sum(
            r: Int,
            c: Int,
            length: Int,
        ): Int =
            if (c >= length) {
                dg2Suffix[r][c] - dg2Suffix[r + length][c - length]
            } else {
                dg2Suffix[r][c]
            }
    }

    private fun isMagicSquare(
        r: Int,
        c: Int,
        size: Int,
        suffixSums: SuffixSums,
    ): Boolean {
        val dg1Sum = suffixSums.dg1Sum(r, c, size)
        val dg2Sum = suffixSums.dg2Sum(r, c + size - 1, size)

        if (dg1Sum != dg2Sum) {
            return false
        }

        val anyBadRow =
            (r..<r + size).any {
                suffixSums.rowSum(it, c, c + size) != dg1Sum
            }
        if (anyBadRow) {
            return false
        }

        val allColumnsOk =
            (c..<c + size).all {
                suffixSums.columnSum(it, r, r + size) == dg1Sum
            }
        return allColumnsOk
    }
}
