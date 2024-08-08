package com.hj.leetcode.kotlin.problem885

/**
 * LeetCode page: [885. Spiral Matrix III](https://leetcode.com/problems/spiral-matrix-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(max(rows, cols)^2) and Space O(rows * cols);
     */
    fun spiralMatrixIII(rows: Int, cols: Int, rStart: Int, cStart: Int): Array<IntArray> {
        val directions = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0),
        )

        val result = Array(rows * cols) { intArrayOf(0, 0) }
        var iResult = 0 // Index to assign the next cell to result
        var row = rStart
        var col = cStart
        var countTurns = 0

        result[iResult][0] = row
        result[iResult][1] = col
        iResult++

        while (iResult < result.size) {
            val direction = directions[countTurns % directions.size]
            val moves = 1 + (countTurns / 2)
            repeat (moves) {
                row += direction[0]
                col += direction[1]

                if (row in 0..<rows && col in 0..<cols) {
                    result[iResult][0] = row
                    result[iResult][1] = col
                    iResult++
                }
            }
            countTurns++
        }
        return result
    }
}