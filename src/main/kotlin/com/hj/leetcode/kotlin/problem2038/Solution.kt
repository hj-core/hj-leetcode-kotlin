package com.hj.leetcode.kotlin.problem2038

/**
 * LeetCode page: [2038. Remove Colored Pieces if Both Neighbors are the Same Color](https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of colors;
     */
    fun winnerOfGame(colors: String): Boolean {
        return maxRemovals(colors, 'A') > maxRemovals(colors, 'B')
    }

    private fun maxRemovals(colors: String, targetColor: Char): Int {
        var result = 0
        var consecutiveLength = 0

        for (color in colors) {
            if (color == targetColor) {
                consecutiveLength++
            } else {
                result += (consecutiveLength - 2).coerceAtLeast(0)
                consecutiveLength = 0
            }
        }
        result += (consecutiveLength - 2).coerceAtLeast(0)
        return result
    }
}