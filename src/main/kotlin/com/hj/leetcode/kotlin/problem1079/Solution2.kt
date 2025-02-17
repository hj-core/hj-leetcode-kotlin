package com.hj.leetcode.kotlin.problem1079

/**
 * LeetCode page: [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities/);
 */
class Solution2 {
    // Complexity:
    // Time O(2^N *N) and Space O(N) where N is the length of `tiles`.
    fun numTilePossibilities(tiles: String): Int {
        val sortedIds = convertToSortedIds(tiles)
        val facTable = factorialTable(tiles.length)
        val result = intArrayOf(0)
        dfs(sortedIds, 0, facTable, IntArray(sortedIds.last() + 1), result)
        return result[0]
    }

    private fun convertToSortedIds(tiles: String): IntArray {
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

    private fun factorialTable(n: Int): IntArray {
        val result = IntArray(n + 1)
        result[0] = 1
        for (i in 1..n) {
            result[i] = result[i - 1] * i
        }
        return result
    }

    // `dfs` accumulates the number of unique permutations to `result[0]`.
    private fun dfs(
        sortedIds: IntArray,
        index: Int,
        factorialTable: IntArray,
        idFrequencies: IntArray,
        result: IntArray,
    ) {
        if (index == sortedIds.size) {
            return
        }
        // Pick the id at index
        idFrequencies[sortedIds[index]]++
        val length = idFrequencies.sum()

        var subResult = factorialTable[length]
        for (freq in idFrequencies) {
            if (freq > 1) {
                subResult /= factorialTable[freq]
            }
        }
        result[0] += subResult
        dfs(sortedIds, index + 1, factorialTable, idFrequencies, result)

        // Backtracking
        idFrequencies[sortedIds[index]]--

        // Not Pick the id at index
        var nextIndex = index + 1
        while (nextIndex < sortedIds.size && sortedIds[nextIndex] == sortedIds[index]) {
            nextIndex++
        }
        dfs(sortedIds, nextIndex, factorialTable, idFrequencies, result)
    }
}
