package com.hj.leetcode.kotlin.problem75

/**
 * LeetCode page: [75. Sort Colors](https://leetcode.com/problems/sort-colors/);
 */
class Solution {
    private val red = 0
    private val white = 1
    private val blue = 2

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun sortColors(nums: IntArray): Unit {
        val numReds = sortRedAndReturnNumReds(nums)
        sortOthersAfterRedAreSorted(nums, numReds)
    }

    private fun sortRedAndReturnNumReds(colors: IntArray): Int {
        var indexNextRed = 0
        for ((index, color) in colors.withIndex()) {
            if (color == red) {
                colors.swap(index, indexNextRed)
                indexNextRed++
            }
        }
        return indexNextRed
    }

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }

    private fun sortOthersAfterRedAreSorted(colors: IntArray, numReds: Int) {
        var index = numReds
        var indexNextBlue = colors.lastIndex
        while (index < indexNextBlue) {
            val color = colors[index]
            if (color == blue) {
                colors.swap(index, indexNextBlue)
                indexNextBlue--
            } else {
                index++
            }
        }
    }
}