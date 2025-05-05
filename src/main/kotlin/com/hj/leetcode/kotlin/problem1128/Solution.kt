package com.hj.leetcode.kotlin.problem1128

/**
 * LeetCode page: [1128. Number of Equivalent Domino Pairs](https://leetcode.com/problems/number-of-equivalent-domino-pairs/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of dominoes.
    fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
        val freq = HashMap<Int, Int>() // entry=(hash, count)
        var result = 0
        for (domino in dominoes) {
            val hash = hashDomino(domino)
            result += freq[hash] ?: 0
            freq[hash] = (freq[hash] ?: 0) + 1
        }
        return result
    }

    private fun hashDomino(domino: IntArray): Int = (domino.min() shl 10) + domino.max()
}
