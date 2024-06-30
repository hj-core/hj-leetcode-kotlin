package com.hj.leetcode.kotlin.problem1624

/**
 * LeetCode page: [1624. Largest Substring Between Two Equal Characters](https://leetcode.com/problems/largest-substring-between-two-equal-characters/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun maxLengthBetweenEqualCharacters(s: String): Int {
        val charsFirstIndex = IntArray(26) { -1 }
        val charsLastIndex = IntArray(26) { -1 }
        for ((index, char) in s.withIndex()) {
            if (charsFirstIndex[char - 'a'] == -1) {
                charsFirstIndex[char - 'a'] = index
            }
            charsLastIndex[char - 'a'] = index
        }

        return charsLastIndex.indices.maxOf {
            charsLastIndex[it] - charsFirstIndex[it] - 1
        }
    }
}