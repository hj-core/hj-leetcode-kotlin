package com.hj.leetcode.kotlin.problem744

/**
 * LeetCode page: [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of letters;
     */
    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        if (target < letters.first()) {
            return letters.first()
        }

        if (target >= letters.last()) {
            return letters.first()
        }

        var lowerBound = 0
        var upperBound = letters.lastIndex
        while (lowerBound < upperBound) {
            val mid = (lowerBound + upperBound) ushr 1
            if (letters[mid] <= target) {
                lowerBound = mid + 1
            } else {
                upperBound = mid
            }
        }
        return letters[lowerBound]
    }
}