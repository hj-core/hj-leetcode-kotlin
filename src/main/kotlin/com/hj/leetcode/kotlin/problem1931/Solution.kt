package com.hj.leetcode.kotlin.problem1931

/**
 * LeetCode page: [1931. Painting a Grid With Three Different Colors](https://leetcode.com/problems/painting-a-grid-with-three-different-colors/);
 */
private typealias Matrix = Array<LongArray>

class Solution {
    private val module: Long = 1_000_000_007

    // Complexity:
    // Time O(8^m * Log(n)) and Space O(4^m).
    fun colorTheGrid(
        m: Int,
        n: Int,
    ): Int {
        require(m <= 5) {
            "The problem constraints have been changed; review required"
        }

        if (n == 1) {
            return 3 * (1 shl (m - 1))
        }

        var mat = buildTransitionMatrix(m) // symmetric
        mat = matrixPow(mat, n - 1, module) // symmetric

        var result = 0L
        for (i in mat.indices) {
            for (j in 0..<i) {
                result = (result + mat[i][j] * 2) % module
            }
            result = (result + mat[i][i]) % module
        }
        return result.toInt()
    }

    // `buildTransitionMatrix` returns a transition matrix that transitions the state
    // from when n equals i to i+1. Here, the state refers to the counts of different
    // color arrangements of an m x n matrix with a specific last-column coloring,
    // covering all possible last-column colorings.
    private fun buildTransitionMatrix(m: Int): Matrix {
        val allRows = generateAllColorRows(m)

        val result = Array(allRows.size) { LongArray(allRows.size) }
        for (i in 0..<allRows.size) {
            for (j in 0..i) {
                if (areRowCompatible(allRows[i], allRows[j], m)) {
                    result[i][j] = 1
                    result[j][i] = 1
                }
            }
        }
        return result
    }

    // `generateAllColorRows` returns a list of all possible color arrangements of a
    // row of length m. The colors are encoded using the lowest 2m bits, with each
    // color takes 2 bits.
    private fun generateAllColorRows(m: Int): IntArray {
        val finalSize = 3 * (1 shl (m - 1))
        val result = IntArray(finalSize)
        val mask = 0b11

        // Base case where m equals 1
        var currSize = 3
        result[0] = 0
        result[1] = 1
        result[2] = 2

        while (currSize < finalSize) {
            var j = currSize * 2 - 1
            for (i in currSize - 1 downTo 0) {
                val base = result[i] shl 2
                val lastColor = result[i] and mask
                for (color in 0..<3) {
                    if (color != lastColor) {
                        result[j] = base or color
                        j--
                    }
                }
            }
            currSize *= 2
        }
        return result
    }

    // `areRowsCompatible` returns whether the rows (bit-encoded) can be placed
    // adjacent to each other.
    private fun areRowCompatible(
        row1: Int,
        row2: Int,
        m: Int,
    ): Boolean {
        var mask = 0b11
        repeat(m) {
            if ((row1 and mask) == (row2 and mask)) {
                return false
            }
            mask = mask shl 2
        }
        return true
    }

    private fun matrixPow(
        base: Matrix,
        power: Int,
        module: Long,
    ): Matrix {
        require(power > 0) { "Unsupported power: $power" }
        if (power == 1) {
            return base
        }

        var result = matrixPow(matrixMul(base, base, module), power / 2, module)
        if (power and 1 == 1) {
            result = matrixMul(result, base, module)
        }
        return result
    }

    private fun matrixMul(
        a: Matrix,
        b: Matrix,
        module: Long,
    ): Matrix {
        require(a[0].size == b.size) { "Incompatible matrix shapes" }
        val m = a.size
        val n = b[0].size
        val result = Array(m) { LongArray(n) }

        for (i in 0..<m) {
            for (j in 0..<n) {
                for (k in 0..<b.size) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % module
                }
            }
        }
        return result
    }
}
