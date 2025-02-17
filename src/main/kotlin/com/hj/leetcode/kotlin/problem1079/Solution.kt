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
        val visitedPath = mutableSetOf<Int>()
        dfs(sortedIds, 0, 0, 0, visitedPath)
        return visitedPath.size
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

    // `dfs` adds the path values of all non-empty permutations (both partial and full)
    // using `sortedIds` to `visitedPath`.
    private fun dfs(
        sortedIds: IntArray,
        index: Int,
        usedBitMap: Int,
        path: Int, // Each id in the path occupies 3 bits
        visitedPath: MutableSet<Int>,
    ) {
        if (index == sortedIds.size) {
            return
        }
        for (i in sortedIds.indices) {
            if (usedBitMap and (1 shl i) != 0) {
                continue
            }
            val newPathValue = path or (sortedIds[i] shl (index * 3))
            if (newPathValue in visitedPath) {
                continue
            }
            visitedPath.add(newPathValue)
            dfs(sortedIds, index + 1, usedBitMap or (1 shl i), newPathValue, visitedPath)
        }
    }
}
