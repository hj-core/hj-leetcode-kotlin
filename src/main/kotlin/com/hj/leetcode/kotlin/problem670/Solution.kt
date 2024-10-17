package com.hj.leetcode.kotlin.problem670

import kotlin.math.max

/**
 * LeetCode page: [670. Maximum Swap](https://leetcode.com/problems/maximum-swap/);
 */
class Solution {
    /* Complexity:
     * Time O(Log(num)) and Space O(1).
     */
    fun maximumSwap(num: Int): Int {
        var iNum = num
        var unit = 1
        var right = Digit(iNum % 10, unit) // Maximum digit encountered
        var maxIncrement = 0

        while (iNum > 0) {
            val left = Digit(iNum % 10, unit)
            if (left.value > right.value) {
                right = left
            } else if (left.value < right.value) {
                val increment = (right.value - left.value) * (left.unit - right.unit)
                maxIncrement = max(maxIncrement, increment)
            }
            iNum /= 10
            unit *= 10
        }
        return num + maxIncrement
    }

    private data class Digit(
        val value: Int,
        val unit: Int,
    )
}
