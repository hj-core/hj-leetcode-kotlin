package com.hj.leetcode.kotlin.problem2211

/**
 * LeetCode page: [2211. Count Collisions on a Road](https://leetcode.com/problems/count-collisions-on-a-road/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of directions.
    fun countCollisions(directions: String): Int {
        val i = directions.indexOfFirst { it != 'L' }
        if (i == -1) {
            return 0
        }
        val j = directions.indexOfLast { it != 'R' }
        // Every L in directions[i..] and every R in directions[..<j]
        // will hit something, each of them contributes one to the
        // count.
        return (i..j).count { directions[it] != 'S' }
    }
}
