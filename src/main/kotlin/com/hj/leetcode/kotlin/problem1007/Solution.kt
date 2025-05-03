package com.hj.leetcode.kotlin.problem1007

/**
 * LeetCode page: [1007. Minimum Domino Rotations For Equal Row](https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of tops and bottoms.
    fun minDominoRotations(
        tops: IntArray,
        bottoms: IntArray,
    ): Int {
        val result = minDominoRotationsOfTarget(tops, bottoms, tops[0])
        if (result != -1 || tops[0] == bottoms[0]) {
            return result
        }
        return minDominoRotationsOfTarget(tops, bottoms, bottoms[0])
    }

    private fun minDominoRotationsOfTarget(
        tops: IntArray,
        bottoms: IntArray,
        target: Int,
    ): Int {
        var rotateTop = 0
        var rotateBottom = 0
        for (i in tops.indices) {
            when {
                tops[i] != target && bottoms[i] != target -> return -1
                tops[i] != target -> rotateTop++
                bottoms[i] != target -> rotateBottom++
                // Both equal target -> NOP
            }
        }
        return minOf(rotateTop, rotateBottom)
    }
}
