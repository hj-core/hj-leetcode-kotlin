package com.hj.leetcode.kotlin.problem1079

/**
 * LeetCode page: [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities/);
 */
class Solution4 {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of `tiles`.
    fun numTilePossibilities(tiles: String): Int {
        val facTable = factorialTable(tiles.length)
        val frequencies = frequencies(tiles)

        // dp[len]@prefixLength ::= the number of unique length `len` permutations
        // using the first `prefixLength` chars of the expanded frequencies.
        val dp = IntArray(tiles.length + 1)

        // Store the new permutations to be added to dp for an extra `freq` of the same characters.
        // This will be reset to zero before processing a new `freq`.
        val addition = IntArray(tiles.length + 1)

        var prefixLength = 0
        dp[0] = 1
        for (freq in frequencies) {
            for (cnt in 1..freq) {
                prefixLength++
                for (i in 0..prefixLength - cnt) {
                    // For each length i permutation, there are (i+1)(i+2)...(i+cnt)/cnt!
                    // ways to insert cnt extra new same chars
                    addition[i + cnt] += dp[i] * facTable[i + cnt] / facTable[i] / facTable[cnt]
                }
            }
            for (i in 0..prefixLength) {
                dp[i] += addition[i]
                addition[i] = 0
            }
        }
        return dp.sum() - 1
    }

    private fun factorialTable(n: Int): IntArray {
        val result = IntArray(n + 1)
        result[0] = 1
        for (i in 1..n) {
            result[i] = result[i - 1] * i
        }
        return result
    }

    private fun frequencies(tiles: String): IntArray =
        tiles
            .groupingBy { it }
            .eachCount()
            .values
            .toIntArray()
}
