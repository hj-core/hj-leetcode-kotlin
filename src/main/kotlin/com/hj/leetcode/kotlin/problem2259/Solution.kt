package com.hj.leetcode.kotlin.problem2259

/**
 * LeetCode page: [2259. Remove Digit From Number to Maximize Result](https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Aux_Space O(1) where N is the length of number;
     */
    fun removeDigit(number: String, digit: Char): String {
        var indexToRemove = -1
        for (index in number.indices) {
            if (number[index] == digit) {
                indexToRemove = index
                if (index + 1 > number.lastIndex || number[index] < number[index + 1]) break
            }
        }

        val ans = StringBuilder(number.length - 1)
        ans.append(number, 0, indexToRemove)
        ans.append(number, indexToRemove + 1, number.length)

        return ans.toString()
    }
}