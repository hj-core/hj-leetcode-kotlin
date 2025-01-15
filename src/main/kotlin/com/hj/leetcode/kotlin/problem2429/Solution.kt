package com.hj.leetcode.kotlin.problem2429

/**
 * LeetCode page: [2429. Minimize XOR](https://leetcode.com/problems/minimize-xor/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun minimizeXor(
        num1: Int,
        num2: Int,
    ): Int {
        val count1 = num1.countOneBits()
        val count2 = num2.countOneBits()

        return when {
            count1 < count2 -> caseNum1LessSetBits(num1, count2 - count1)
            count1 > count2 -> caseNum1MoreSetBits(num1, count1 - count2)
            else -> num1
        }
    }

    private fun caseNum1MoreSetBits(
        num1: Int,
        diff: Int,
    ): Int {
        // Compensate for the diff by clearing the first diff set bits,
        // starting from the LSB.
        var result = num1
        repeat(diff) {
            result = result and (result - 1)
        }
        return result
    }

    private fun caseNum1LessSetBits(
        num1: Int,
        diff: Int,
    ): Int {
        // Compensate for the diff by setting the first diff unset bits,
        // starting from the smallest position.
        var result = num1
        repeat(diff) {
            result = result or (result + 1)
        }
        return result
    }
}
