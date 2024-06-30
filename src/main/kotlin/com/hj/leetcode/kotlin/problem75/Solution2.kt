package com.hj.leetcode.kotlin.problem75

/**
 * LeetCode page: [75. Sort Colors](https://leetcode.com/problems/sort-colors/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums;
     */
    fun sortColors(nums: IntArray): Unit {
        var i = 0
        for ((color, count) in countColors(nums).withIndex()) {
            repeat(count) {
                nums[i] = color
                i++
            }
        }
    }

    private fun countColors(colors: IntArray): IntArray {
        val result = IntArray(3)
        for (color in colors) {
            result[color]++
        }
        return result
    }
}