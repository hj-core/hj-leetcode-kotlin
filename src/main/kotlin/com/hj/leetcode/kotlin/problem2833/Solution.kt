package com.hj.leetcode.kotlin.problem2833

/**
 * LeetCode page: [2833. Furthest Point From Origin](https://leetcode.com/problems/furthest-point-from-origin/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of moves.
    fun furthestDistanceFromOrigin(moves: String): Int {
        // count of ('L', not_unused, 'R' and '_')
        val count = IntArray(4)
        for (move in moves) {
            count[move.code and 3]++
        }
        return moves.length - minOf(count[0], count[2]) * 2
    }
}
