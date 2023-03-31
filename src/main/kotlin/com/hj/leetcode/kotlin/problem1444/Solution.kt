package com.hj.leetcode.kotlin.problem1444

/**
 * LeetCode page: [1444. Number of Ways of Cutting a Pizza](https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/);
 */
class Solution {

    private val mod = 1_000_000_007

    /* Complexity:
     * Time O(kMN(M+N)) and Space O(MN) where M and N are the number of rows and columns;
     */
    fun ways(pizza: Array<String>, k: Int): Int {
        // suffixesAppleCount[r][c] ::= the number of apples on pizza[r until numRows][c until numColumns]
        val suffixesAppleCount = suffixesAppleCount(pizza)

        val noEnoughApples = suffixesAppleCount[0][0] < k
        if (noEnoughApples) return 0

        var currentPieces = 1
        // subResults[r][c] ::= ways(pizza[r until numRows][c until numColumns], currentPieces)
        var subResults = subResultsForSinglePiece(pizza, suffixesAppleCount)
        while (currentPieces < k) {
            subResults = subResultsForOneMorePieces(currentPieces, subResults, suffixesAppleCount)
            currentPieces++
        }
        return subResults[0][0].toInt()
    }

    private fun suffixesAppleCount(pizza: Array<String>): Array<IntArray> {
        val numRows = pizza.size
        val numColumns = pizza[0].length
        val suffixAppleCount = Array(numRows + 1) { IntArray(numColumns + 1) }
        for (row in numRows - 1 downTo 0) {
            for (column in numColumns - 1 downTo 0) {
                val cellValue = pizza[row][column]
                val cellCount = if (isApple(cellValue)) 1 else 0
                suffixAppleCount[row][column] = cellCount +
                        suffixAppleCount[row + 1][column] +
                        suffixAppleCount[row][column + 1] -
                        suffixAppleCount[row + 1][column + 1]
            }
        }
        return suffixAppleCount
    }

    private fun isApple(value: Char): Boolean = value == 'A'

    private fun subResultsForSinglePiece(pizza: Array<String>, suffixAppleCounts: Array<IntArray>): Array<LongArray> {
        val numRows = pizza.size
        val numColumns = pizza[0].length
        val subResults = Array(numRows) { LongArray (numColumns) }
        for (row in numRows - 1 downTo 0) {
            for (column in numColumns - 1 downTo 0) {
                val isValidPiece = suffixAppleCounts[row][column] > 0
                subResults[row][column] = if (isValidPiece) 1 else 0
            }
        }
        return subResults
    }

    private fun subResultsForOneMorePieces(
        currentPieces: Int,
        currentResults: Array<LongArray>,
        suffixAppleCounts: Array<IntArray>
    ): Array<LongArray> {
        val numRows = currentResults.size
        val numColumns = currentResults[0].size
        val newPieces = currentPieces + 1
        val newResult = Array(numRows) { LongArray(numColumns) }
        for (row in numRows - 1 downTo 0) {
            for (column in numColumns - 1 downTo  0) {
                val noEnoughApples = suffixAppleCounts[row][column] < newPieces
                if (noEnoughApples) continue

                var numCuttingWays = 0L
                // Examine all possible horizontal cuts
                for (rowCut in row until numRows - 1) {
                    val isValidCut =
                        suffixAppleCounts[row][column] - suffixAppleCounts[rowCut + 1][column] > 0
                    if (isValidCut) {
                        numCuttingWays += currentResults[rowCut + 1][column]
                    }
                }
                // Examine all possible vertical cuts
                for (columnCut in column until numColumns - 1) {
                    val isValidCut =
                        suffixAppleCounts[row][column] - suffixAppleCounts[row][columnCut + 1] > 0
                    if (isValidCut) {
                        numCuttingWays += currentResults[row][columnCut + 1]
                    }
                }
                newResult[row][column] = numCuttingWays % mod
            }
        }
        return newResult
    }
}