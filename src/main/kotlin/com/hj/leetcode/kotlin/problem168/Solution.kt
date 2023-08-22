package com.hj.leetcode.kotlin.problem168

/**
 * LeetCode page: [168. Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(LogN) where N equals columnNumber;
     */
    fun convertToTitle(columnNumber: Int): String {
        val result = StringBuilder()
        var number = columnNumber
        while (number > 0) {
            number--
            result.append('A' + (number % 26))
            number /= 26
        }
        return result.reverse().toString()
    }
}