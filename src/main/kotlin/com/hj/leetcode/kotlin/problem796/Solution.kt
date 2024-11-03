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
}

private class KMP(
    val target: String,
) {
    // kmpTable[i]::=
    // length of target's longest prefix that is a suffix of target[1..i]
    private val kmpTable = buildKmpTable(target)

    private fun buildKmpTable(target: String): IntArray {
        val result = IntArray(target.length)
        var compareIndex = 0
        for (i in 1..<target.length) {
            while (compareIndex > 0 && target[i] != target[compareIndex]) {
                compareIndex = result[compareIndex - 1]
            }
            if (target[i] == target[compareIndex]) {
                compareIndex += 1
            }
            result[i] = compareIndex
        }
        return result
    }

    fun searchIn(s: String): Boolean {
        var compareIndex = 0
        for (i in s.indices) {
            while (compareIndex > 0 && s[i] != target[compareIndex]) {
                compareIndex = kmpTable[compareIndex - 1]
            }
            if (s[i] == target[compareIndex]) {
                compareIndex += 1
            }

            if (compareIndex == target.length) {
                return true
            }
            if (s.length - i < target.length - compareIndex) {
                return false
            }
        }
        return false
    }
}
