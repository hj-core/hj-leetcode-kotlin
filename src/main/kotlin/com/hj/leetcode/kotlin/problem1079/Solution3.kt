package com.hj.leetcode.kotlin.problem1079

/**
 * LeetCode page: [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities/);
 */
class Solution3 {
    // Complexity:
    // Time O(N! *N) and Space O(N) where N is the length of `tiles`
    fun numTilePossibilities(tiles: String): Int = dfs(frequencies(tiles))

    private fun frequencies(tiles: String): IntArray =
        tiles
            .groupingBy { it }
            .eachCount()
            .values
            .toIntArray()

    // `dfs` returns the number of unique non-empty permutations (both partial and full)
    // using the frequencies.
    private fun dfs(frequencies: IntArray): Int {
        var result = 0
        for (i in frequencies.indices) {
            if (frequencies[i] > 0) {
                frequencies[i]--
                result += 1 + dfs(frequencies)
                frequencies[i]++
            }
        }
        return result
    }
}
