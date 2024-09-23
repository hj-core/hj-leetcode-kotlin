package com.hj.leetcode.kotlin.problem2707

import kotlin.math.min

/**
 * LeetCode page: [2707. Extra Characters in a String](https://leetcode.com/problems/extra-characters-in-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N^3+M) and Space O(N^2+M) where N is the length of s
     * and M is the flattened length of dictionary.
     */
    fun minExtraChar(
        s: String,
        dictionary: Array<String>,
    ): Int = minExtraChar(s, 0, s.length, inDictionary(dictionary, s), mutableMapOf())

    private fun minExtraChar(
        s: String,
        from: Int,
        until: Int,
        inDictionary: Array<BooleanArray>,
        memoization: MutableMap<IntRange, Int>,
    ): Int {
        if (from == until) {
            return 0
        }
        val range = from..<until
        if (range in memoization) {
            return checkNotNull(memoization[range])
        }

        var result = until - from
        // Try all possible lengths of the first split
        for (firstLength in 1..(until - from)) {
            val leftExtra = if (inDictionary[from][firstLength]) 0 else firstLength
            val rightExtra = minExtraChar(s, from + firstLength, until, inDictionary, memoization)
            result = min(result, leftExtra + rightExtra)
        }
        memoization[range] = result
        return result
    }

    private fun inDictionary(
        dictionary: Array<String>,
        s: String,
    ): Array<BooleanArray> {
        val dictionarySet = dictionary.toSet()
        val result =
            Array(s.length) { from ->
                BooleanArray(s.length - from + 1)
            }

        for (from in s.indices) {
            for (length in 1..(s.length - from)) {
                if (s.substring(from..<from + length) in dictionarySet) {
                    result[from][length] = true
                }
            }
        }
        return result
    }
}
