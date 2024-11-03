package com.hj.leetcode.kotlin.problem796

/**
 * LeetCode page: [796. Rotate String](https://leetcode.com/problems/rotate-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the smaller length between s and goal.
     */
    fun rotateString(
        s: String,
        goal: String,
    ): Boolean = s.length == goal.length && KMP(goal).searchIn(s + s)

    private class KMP(
        val substring: String,
    ) {
        // kmpTable[i]::=
        // length of the longest prefix of substring that is a suffix of substring[1..i]
        private val kmpTable = buildKmpTable(substring)

        private fun buildKmpTable(substring: String): IntArray {
            val result = IntArray(substring.length)
            var compareIndex = 0
            for (i in 1..<substring.length) {
                while (compareIndex > 0 && substring[i] != substring[compareIndex]) {
                    compareIndex = result[compareIndex - 1]
                }
                if (substring[i] == substring[compareIndex]) {
                    compareIndex += 1
                }
                result[i] = compareIndex
            }
            return result
        }

        fun searchIn(s: String): Boolean {
            var compareIndex = 0
            for (i in s.indices) {
                while (compareIndex > 0 && s[i] != substring[compareIndex]) {
                    compareIndex = kmpTable[compareIndex - 1]
                }
                if (s[i] == substring[compareIndex]) {
                    compareIndex += 1
                }

                if (compareIndex == substring.length) {
                    return true
                }
                if (s.length - i < substring.length - compareIndex) {
                    return false
                }
            }
            return false
        }
    }
}
