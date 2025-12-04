package com.hj.leetcode.kotlin.problem2211

/**
 * LeetCode page: [2211. Count Collisions on a Road](https://leetcode.com/problems/count-collisions-on-a-road/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of directions.
    fun countCollisions(directions: String): Int {
        var i = directions.indexOfFirst { it != 'L' }
        if (i == -1) {
            return 0
        } // Every L in directions[i..] will hit something

        var result = 0
        var countR = 0
        while (i < directions.length) {
            when (directions[i]) {
                'R' -> {
                    countR++
                }

                'L' -> {
                    result += 1 + countR
                    countR = 0
                }

                else -> {
                    result += countR
                    countR = 0
                }
            }
            i++
        }
        return result
    }
}
