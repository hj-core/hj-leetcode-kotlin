package com.hj.leetcode.kotlin.problem260

/**
 * LeetCode page: [260. Single Number III](https://leetcode.com/problems/single-number-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums;
     */
    fun singleNumber(nums: IntArray): IntArray {
        // Denote the two single numbers as x and y
        val xXorY = nums.reduce { acc, i -> acc xor i }
        // Any bit that is different between x and y
        val bitMask = xXorY.leastSignificantBit()

        val x = nums.fold(0) { acc, num ->
            if (num and bitMask != 0) acc xor num else acc
        }
        val y = xXorY xor x
        return intArrayOf(x, y)
    }

    private fun Int.leastSignificantBit() = if (this == Int.MIN_VALUE) {
        this
    } else {
        this - (this and (this - 1))
    }
}