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

        val stringPerRow = List(numRows) { StringBuilder() }
        val lastRowIndex = numRows - 1
        var rowToAppend = 0
        var rowStepToNextAppend = 1
        for (char in s) {
            stringPerRow[rowToAppend].append(char)
            rowToAppend += rowStepToNextAppend
            when (rowToAppend) {
                0 -> rowStepToNextAppend = 1
                lastRowIndex -> rowStepToNextAppend = -1
            }
        }
        return stringPerRow.joinToString(separator = "")
    }
}