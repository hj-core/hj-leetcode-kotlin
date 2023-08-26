package com.hj.leetcode.kotlin.problem646

/**
 * LeetCode page: [646. Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of pairs;
     */
    fun findLongestChain(pairs: Array<IntArray>): Int {
        val sortedPairs = pairs.sortedBy { it[1] }
        var result = 0
        var tailSecond = Int.MIN_VALUE

        for ((first, second) in sortedPairs) {
            if (first > tailSecond) {
                result ++
                tailSecond = second
            }
        }
        return result
    }
}