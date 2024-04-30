package com.hj.leetcode.kotlin.problem1915

/**
 * LeetCode page: [1915. Number of Wonderful Substrings](https://leetcode.com/problems/number-of-wonderful-substrings/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(min(2^M, N)) where M is the size of charset
     * and N is the length of word;
     */
    fun wonderfulSubstrings(word: String): Long {
        /* Use a 10-bit number to represent the character occurrence parity
         * from 'a' to 'j'.
         */

        val charMasks = IntArray(10) { 1 shl it }
        var prefixMask = 0
        val countPrefixMasks = hashMapOf(0 to 1)
        var result = 0L

        for (char in word) {
            prefixMask = prefixMask xor charMasks[char - 'a']

            result += countPrefixMasks[prefixMask] ?: 0
            for (mask in charMasks) {
                result += countPrefixMasks[prefixMask xor mask] ?: 0
            }
            countPrefixMasks[prefixMask] = 1 + (countPrefixMasks[prefixMask] ?: 0)
        }
        return result
    }
}