package com.hj.leetcode.kotlin.problem6

/**
 * LeetCode page: [6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/);
 */
class Solution2 {
    /* Complexity:
     * Time O(|s|) and Space O(|s|);
     */
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s // Edge case that cycleStep equals 0;

        val converted = StringBuilder()
        val cycleStep = 2 * (numRows - 1)

        for (row in 0 until numRows) {
            if (row == 0 || row == numRows - 1) {
                // Case that only one char is appended in each cycle;
                for (index in row until s.length step cycleStep) {
                    converted.append(s[index])
                }
            } else {
                // Case that two chars are appended in each cycle;
                for (index in row until s.length step cycleStep) {
                    converted.append(s[index])

                    val anotherIndex = index + cycleStep - row * 2
                    if (anotherIndex < s.length) {
                        converted.append(s[anotherIndex])
                    }
                }
            }
        }
        return converted.toString()
    }
}