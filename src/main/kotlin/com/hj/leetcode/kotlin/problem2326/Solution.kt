package com.hj.leetcode.kotlin.problem2326

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2326. Spiral Matrix IV](https://leetcode.com/problems/spiral-matrix-iv/);
 */
class Solution {
    /* Complexity:
     * Time O(mn) and Auxiliary Space O(1).
     */
    fun spiralMatrix(
        m: Int,
        n: Int,
        head: ListNode?,
    ): Array<IntArray> {
        val result = Array(m) { IntArray(n) { -1 } }
        val directions =
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 0),
                intArrayOf(0, -1),
                intArrayOf(-1, 0),
            )
        val position = intArrayOf(0, -1) // (row, col)
        var turns = 0
        var nodePtr = head

        while (nodePtr != null) { // By problem constraint head.size <= mn
            val direction = directions[turns % 4]
            val steps = (if (turns % 2 == 0) n else m) - (turns + 1) / 2
            for (i in 0..<steps) {
                if (nodePtr == null) {
                    break
                }
                position[0] += direction[0]
                position[1] += direction[1]
                val (row, col) = position
                result[row][col] = nodePtr.`val`
                nodePtr = nodePtr.next
            }
            turns += 1
        }
        return result
    }
}
