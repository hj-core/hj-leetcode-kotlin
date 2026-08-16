package com.hj.leetcode.kotlin.problem2029

import kotlin.math.abs

/**
 * LeetCode page: [2029. Stone Game IX](https://leetcode.com/problems/stone-game-ix/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of stones.
    fun stoneGameIX(stones: IntArray): Boolean {
        val remCount = IntArray(3)
        for (stone in stones) {
            remCount[stone % 3]++
        }

        if (remCount[0] and 1 == 0) {
            return remCount[1] >= 1 && remCount[2] >= 1
        }
        return abs(remCount[1] - remCount[2]) > 2
    }
}
