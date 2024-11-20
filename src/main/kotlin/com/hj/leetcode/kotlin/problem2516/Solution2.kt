package com.hj.leetcode.kotlin.problem2516

/**
 * LeetCode page: [2516. Take K of Each Character From Left and Right](https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun takeCharacters(
        s: String,
        k: Int,
    ): Int {
        val counts = intArrayOf(0, 0, 0) // count[a, b, c]
        for (c in s) {
            counts[c - 'a'] += 1
        }
        if (counts.any { it < k }) {
            return -1
        }
        // Consider s as circular:
        // Expand the removal window or rotate s counter-clockwise
        var rotations = 0
        for (i in s.indices) {
            counts[s[i] - 'a'] -= 1
            if (counts.any { it < k }) {
                counts[s[rotations] - 'a'] += 1
                rotations += 1
            }
        }
        return rotations
    }
}
