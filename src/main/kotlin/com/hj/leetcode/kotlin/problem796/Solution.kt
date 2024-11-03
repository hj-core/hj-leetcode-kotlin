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
    private val kmpTable = IntArray(target.length)

    init {
        buildTable()
    }

    private tailrec fun buildTable(
        index: Int = 1,
        compareIndex: Int = 0,
    ) {
        when {
            index == target.length -> { // Finish the whole table
            }

            target[index] == target[compareIndex] -> {
                kmpTable[index] = compareIndex + 1
                buildTable(index + 1, compareIndex + 1)
            }

            compareIndex == 0 -> {
                kmpTable[index] = 0
                buildTable(index + 1, 0)
            }

            else -> buildTable(index, kmpTable[compareIndex - 1])
        }
    }

    fun searchIn(s: String): Boolean = searchIn(s, 0, 0)

    private tailrec fun searchIn(
        s: String,
        start: Int,
        compareIndex: Int,
    ): Boolean =
        when {
            compareIndex == target.length -> true
            s.length - start < target.length - compareIndex -> false
            s[start] == target[compareIndex] -> searchIn(s, start + 1, compareIndex + 1)
            compareIndex == 0 -> searchIn(s, start + 1, 0)
            else -> searchIn(s, start, kmpTable[compareIndex - 1])
        }
}
