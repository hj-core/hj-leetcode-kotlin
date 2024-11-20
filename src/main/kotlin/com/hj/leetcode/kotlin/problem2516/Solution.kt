package com.hj.leetcode.kotlin.problem2516

import kotlin.math.min

/**
 * LeetCode page: [2516. Take K of Each Character From Left and Right](https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun takeCharacters(
        s: String,
        k: Int,
    ): Int {
        // s = s[0..=left] + unused + s[right..<s.length]
        val count = IntArray(3) // count[a, b, c]
        var right = s.lastIndex
        while (0 <= right && count.any { it < k }) {
            count[s[right] - 'a'] += 1
            right -= 1
        }
        right += 1 // right for left=-1

        if (count.any { it < k }) {
            return -1
        }
        var result = s.length - right
        for (left in s.indices) {
            count[s[left] - 'a'] += 1
            while (right < s.length && k < count[s[right] - 'a']) {
                count[s[right] - 'a'] -= 1
                right += 1
            }
            result = min(result, left + 1 + s.length - right)
        }
        return result
    }
}
