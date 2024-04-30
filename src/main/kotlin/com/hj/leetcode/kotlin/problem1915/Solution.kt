package com.hj.leetcode.kotlin.problem1915

/**
 * LeetCode page: [1915. Number of Wonderful Substrings](https://leetcode.com/problems/number-of-wonderful-substrings/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(M) where M is the size of charset
     * and N is the size of words;
     */
    fun wonderfulSubstrings(word: String): Long {
        val charMasks = IntArray(10) { 1 shl it } // 1, 2, 4, ... for a, b, c, ...
        var prefixMask = 0 // 10-bit number,
        val countPrefixMask = hashMapOf(0 to 1)
        var result = 0L

        for (char in word) {
            prefixMask = prefixMask xor charMasks[char - 'a']
            result += countPrefixMask[prefixMask] ?: 0
            for (mask in charMasks) {
                result += countPrefixMask[prefixMask xor mask] ?: 0
            }
            countPrefixMask[prefixMask] = 1 + (countPrefixMask[prefixMask] ?: 0)
        }
        return result
    }
}