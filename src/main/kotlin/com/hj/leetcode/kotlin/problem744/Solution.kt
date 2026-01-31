package com.hj.leetcode.kotlin.problem744

/**
 * LeetCode page: [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target/);
 */
class Solution {
    // Complexity:
    // Time O(LogN) and Space O(1) where N is the length of letters.
    fun nextGreatestLetter(
        letters: CharArray,
        target: Char,
    ): Char {
        if (letters.last() <= target) {
            return letters.first()
        }

        // Binary search for the next greater index, which is in the
        // range [left, right].
        var left = 0
        var right = letters.lastIndex
        while (left < right) {
            val mid = (left + right) ushr 1
            if (letters[mid] <= target) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return letters[left]
    }
}
