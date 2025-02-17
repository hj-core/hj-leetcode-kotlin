package com.hj.leetcode.kotlin.problem1079

/**
 * LeetCode page: [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities/);
 */
class Solution {
    // Complexity:
    // Time O(N!*N) and Space O(N!) where N is the length of `tiles`.
    fun numTilePossibilities(tiles: String): Int {
        require(tiles.length <= 7)
        val ids = convertToIds(tiles)
        val permutations = mutableSetOf<Int>()
        dfs(0, ids, 0, 0, permutations)
        return permutations.size
    }

    private fun convertToIds(tiles: String): IntArray {
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

    // `dfs` add the path values of all non-empty (partial or full) permutations of ids to `permutations`
    private fun dfs(
        index: Int,
        ids: IntArray,
        usedBitMap: Int,
        pathValue: Int, // each index occupies 3-bit for the id at that index
        permutations: MutableSet<Int>,
    ) {
        if (index == ids.size) {
            return
        }
        for (i in ids.indices) {
            if (usedBitMap and (1 shl i) != 0) {
                continue
            }
            val newPathValue = pathValue or (ids[i] shl (index * 3))
            if (newPathValue in permutations) {
                continue
            }
            permutations.add(newPathValue)
            dfs(index + 1, ids, usedBitMap or (1 shl i), newPathValue, permutations)
        }
    }
}
