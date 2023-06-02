package com.hj.leetcode.kotlin.problem2101

/**
 * LeetCode page: [2101. Detonate the Maximum Bombs](https://leetcode.com/problems/detonate-the-maximum-bombs/);
 */
class Solution {
    /* Complexity:
     * Time O(N^3) and Space O(N^2) where N is the size of bombs;
     */
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        val bombAdjacencyInIndex = bombAdjacencyInIndex(bombs)
        var result = 0
        for (sourceBombIndex in bombs.indices) {
            val numDetonatedBombs = numDetonatedBombs(sourceBombIndex, bombs, bombAdjacencyInIndex)
            if (result < numDetonatedBombs) {
                result = numDetonatedBombs
            }
        }
        return result
    }

    private fun bombAdjacencyInIndex(bombs: Array<IntArray>): List<List<Int>> {
        val result = List(bombs.size) { mutableListOf<Int>() }
        for (i in bombs.indices) {
            for (j in i + 1 until bombs.size) {
                if (inRange(bombs[i], bombs[j])) {
                    result[i].add(j)
                }
                if (inRange(bombs[j], bombs[i])) {
                    result[j].add(i)
                }
            }
        }
        return result
    }

    private fun inRange(sourceBomb: IntArray, nearbyBomb: IntArray): Boolean {
        val (sourceX, sourceY, sourceRadius) = sourceBomb
        val (nearbyX, nearbyY, _) = nearbyBomb
        val distanceSquare = square(sourceX - nearbyX) + square(sourceY - nearbyY)
        return distanceSquare <= square(sourceRadius)
    }

    private fun square(a: Int): Long {
        return a.toLong() * a
    }

    private fun numDetonatedBombs(
        sourceBombIndex: Int,
        bombs: Array<IntArray>,
        bombAdjacencyInIndex: List<List<Int>>,
        hasBeenDetonated: BooleanArray = BooleanArray(bombs.size)
    ): Int {

        if (hasBeenDetonated[sourceBombIndex]) {
            return 0
        }
        hasBeenDetonated[sourceBombIndex] = true

        var result = 1
        val adjacentBombIndex = bombAdjacencyInIndex[sourceBombIndex]
        for (index in adjacentBombIndex) {
            result += numDetonatedBombs(index, bombs, bombAdjacencyInIndex, hasBeenDetonated)
        }
        return result
    }
}