package com.hj.leetcode.kotlin.problem657

/**
 * LeetCode page: [657. Robot Return to Origin](https://leetcode.com/problems/robot-return-to-origin/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of moves.
    fun judgeCircle(moves: String): Boolean {
        var dx = 0
        var dy = 0
        for (move in moves) {
            when (move) {
                'U' -> dy++
                'D' -> dy--
                'L' -> dx++
                'R' -> dx--
            }
        }

        return dx == 0 && dy == 0
    }
}
