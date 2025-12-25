package com.hj.leetcode.kotlin.problem3074

/**
 * LeetCode page: [3074. Apple Redistribution into Boxes](https://leetcode.com/problems/apple-redistribution-into-boxes/);
 */
class Solution {
    // Complexity:
    // Time O(N+MLogM) and Space O(M) where N is the length of apple
    // and M is the length of capacity.
    fun minimumBoxes(
        apple: IntArray,
        capacity: IntArray,
    ): Int {
        val boxes = capacity.sortedArrayDescending()
        var pending = apple.sum()

        for ((i, boxSize) in boxes.withIndex()) {
            pending -= boxSize

            if (pending <= 0) {
                return i + 1
            }
        }
        throw IllegalArgumentException("Cannot pack all apples")
    }
}
