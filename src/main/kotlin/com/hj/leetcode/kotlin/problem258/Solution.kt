package com.hj.leetcode.kotlin.problem258

/**
 * LeetCode page: [258. Add Digits](https://leetcode.com/problems/add-digits/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun addDigits(num: Int): Int {
        if (num == 0) return 0
        return (num % 9).let { if (it == 0) 9 else it }
    }
}