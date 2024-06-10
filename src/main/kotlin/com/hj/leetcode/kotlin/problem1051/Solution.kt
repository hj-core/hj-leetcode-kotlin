package com.hj.leetcode.kotlin.problem1051

/**
 * LeetCode page: [1051. Height Checker](https://leetcode.com/problems/height-checker/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of heights;
     */
    fun heightChecker(heights: IntArray): Int {
        val sortedHeights = heights.sorted()
        return (heights.indices).count {
            heights[it] != sortedHeights[it]
        }
    }
}