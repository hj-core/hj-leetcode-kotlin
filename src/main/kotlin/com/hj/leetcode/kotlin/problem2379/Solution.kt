package com.hj.leetcode.kotlin.problem2379

/**
 * LeetCode page: [2379. Minimum Recolors to Get K Consecutive Black Blocks](https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of blocks.
    fun minimumRecolors(
        blocks: String,
        k: Int,
    ): Int {
        var windowWhite = (0..<k).count { blocks[it] == 'W' }
        var result = windowWhite

        for (i in k..<blocks.length) {
            if (blocks[i] == 'W') {
                windowWhite++
            }
            if (blocks[i - k] == 'W') {
                windowWhite--
            }
            if (windowWhite < result) {
                result = windowWhite
            }
        }
        return result
    }
}
