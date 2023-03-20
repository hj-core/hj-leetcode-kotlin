package com.hj.leetcode.kotlin.problem605

/**
 * LeetCode page: [605. Can Place Flowers](https://leetcode.com/problems/can-place-flowers/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of flowerbed;
     */
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        if (n == 0) return true
        if (n > flowerbed.size) return false

        var currPlot = 0
        var remainingFlowers = n

        while (currPlot < flowerbed.size) {
            val hasFlower = flowerbed[currPlot] == 1
            if (hasFlower) {
                currPlot++
            } else {
                val emptyLength = findEmptyLength(flowerbed, currPlot)
                val effectiveEmptyLength = computeEffectiveEmptyLength(flowerbed, currPlot, emptyLength)

                remainingFlowers -= computeMaxFlowers(effectiveEmptyLength)
                if (remainingFlowers <= 0) return true
                currPlot += emptyLength + 1
            }
        }
        return false
    }

    private fun findEmptyLength(flowerbed: IntArray, currPlot: Int): Int {
        var nextNonEmpty = currPlot
        while (nextNonEmpty < flowerbed.size && flowerbed[nextNonEmpty] == 0) {
            nextNonEmpty++
        }
        return nextNonEmpty - currPlot
    }

    private fun computeEffectiveEmptyLength(flowerbed: IntArray, currPlot: Int, emptyLength: Int): Int {
        require(emptyLength >= 0)
        return emptyLength
            .let {
                val hasFlowerBefore = flowerbed.getOrNull(currPlot - 1) == 1
                if (hasFlowerBefore) it - 1 else it
            }
            .let {
                val hasFlowerAfter = flowerbed.getOrNull(currPlot + emptyLength) == 1
                if (hasFlowerAfter) it - 1 else it
            }
    }

    private fun computeMaxFlowers(effectiveEmptyLength: Int): Int {
        return ((effectiveEmptyLength + 1) shr 1).coerceAtLeast(0)
    }
}