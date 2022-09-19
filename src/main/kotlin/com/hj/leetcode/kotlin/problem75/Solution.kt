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
        var indexNextRed = 0
        for ((index, color) in nums.withIndex()) {
            if (color == red) {
                nums.swap(index, indexNextRed)
                indexNextRed++
            }
        }

        var index = indexNextRed
        var indexNextBlue = nums.lastIndex
        while (index < indexNextBlue) {
            val color = nums[index]
            if (color == blue) {
                nums.swap(index, indexNextBlue)
                indexNextBlue--
            } else {
                index++
            }
        }
    }

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }
}