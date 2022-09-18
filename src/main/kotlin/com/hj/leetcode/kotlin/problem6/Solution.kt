package com.hj.leetcode.kotlin.problem6

/**
 * LeetCode page: [6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s

        val strEachRow = List(numRows) { StringBuilder() }
        val lastRow = numRows - 1
        var currRow = 0
        var step = 1
        for (char in s) {
            strEachRow[currRow].append(char)
            currRow += step
            when (currRow) {
                0 -> step = 1
                lastRow -> step = -1
            }
        }
        return strEachRow.joinToString(separator = "")
    }
}