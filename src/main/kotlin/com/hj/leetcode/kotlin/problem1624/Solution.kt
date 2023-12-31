package com.hj.leetcode.kotlin.problem1624

import kotlin.math.max

/**
 * LeetCode page: [1624. Largest Substring Between Two Equal Characters](https://leetcode.com/problems/largest-substring-between-two-equal-characters/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun maxLengthBetweenEqualCharacters(s: String): Int {
        val charFirstIndex = IntArray(26) { -1 }
        var result = -1
        for ((index, char) in s.withIndex()) {
            val first = charFirstIndex[char - 'a']
            if (first == -1) {
                charFirstIndex[char - 'a'] = index
            } else {
                result = max(result, index - first - 1)
            }
        }
        return result
    }
}