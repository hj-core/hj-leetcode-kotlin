package com.hj.leetcode.kotlin.problem1079

/**
 * LeetCode page: [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities/);
 */
class Solution {
    // Complexity:
    // Time O(N!*N) and Space O(N!) where N is the length of `tiles`.
    fun numTilePossibilities(tiles: String): Int {
        require(tiles.length <= 7)
        val sortedIds = convertToSortedIds(tiles)
        val visitedPathValues = mutableSetOf<Int>()
        dfs(sortedIds, 0, 0, 0, visitedPathValues)
        return visitedPathValues.size
    }

    private fun convertToSortedIds(tiles: String): IntArray {
        val sorted = tiles.toCharArray().sorted()
        val result = IntArray(sorted.size)
        var id = 1

        result[0] = id
        for (i in 1..<result.size) {
            if (sorted[i] != sorted[i - 1]) {
                id++
            }
            result[i] = id
        }
        return result
    }

    // `dfs` adds the path values of all non-empty (partial or full) permutations of ids to the `visitedPathValues`.
    private fun dfs(
        sortedIds: IntArray,
        index: Int,
        usedBitMap: Int,
        pathValue: Int, // each index occupies 3-bit for the id at that index
        visitedPathValues: MutableSet<Int>,
    ) {
        if (index == sortedIds.size) {
            return
        }
        for (i in sortedIds.indices) {
            if (usedBitMap and (1 shl i) != 0) {
                continue
            }
            val newPathValue = pathValue or (sortedIds[i] shl (index * 3))
            if (newPathValue in visitedPathValues) {
                continue
            }
            visitedPathValues.add(newPathValue)
            dfs(sortedIds, index + 1, usedBitMap or (1 shl i), newPathValue, visitedPathValues)
        }
    }
}
