package com.hj.leetcode.kotlin.problem1079

/**
 * LeetCode page: [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities/);
 */
class Solution2 {
    // Complexity:
    // Time O(2^N *N) and Space O(N) where N is the length of `tiles`.
    fun numTilePossibilities(tiles: String): Int {
        val ids = convertToIds(tiles)
        val facTable = factorialTable(tiles.length)
        val result = intArrayOf(0)
        dfs(ids, 0, facTable, IntArray(ids.last() + 1), result)
        return result[0]
    }

    private fun factorialTable(n: Int): IntArray {
        val result = IntArray(n + 1)
        result[0] = 1
        for (i in 1..n) {
            result[i] = result[i - 1] * i
        }
        return result
    }

    private fun convertToIds(tiles: String): IntArray {
        val sorted = tiles.toCharArray().sorted()
        val result = IntArray(sorted.size)
        var id = 0
        for (i in 1..<result.size) {
            if (sorted[i] != sorted[i - 1]) {
                id++
            }
            result[i] = id
        }
        return result
    }

    private fun dfs(
        ids: IntArray,
        index: Int,
        factorialTable: IntArray,
        idFrequencies: IntArray,
        result: IntArray,
    ) {
        if (index == ids.size) {
            return
        }
        // Pick the id at index
        idFrequencies[ids[index]]++
        val length = idFrequencies.sum()

        var subResult = factorialTable[length]
        for (freq in idFrequencies) {
            if (freq > 1) {
                subResult /= factorialTable[freq]
            }
        }
        result[0] += subResult
        dfs(ids, index + 1, factorialTable, idFrequencies, result)

        // Backtracking
        idFrequencies[ids[index]]--

        // Not Pick the id at index
        var nextIndex = index + 1
        while (nextIndex < ids.size && ids[nextIndex] == ids[index]) {
            nextIndex++
        }
        dfs(ids, nextIndex, factorialTable, idFrequencies, result)
    }
}
