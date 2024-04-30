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
        /* Use a 10-bit number to represent if the occurrence of a to j
         * is odd or not.
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