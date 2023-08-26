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
        var chainTail = intArrayOf(Int.MIN_VALUE, Int.MIN_VALUE)

        for (pair in sortedPairs) {
            if (pair[0] > chainTail[1]) {
                result ++
                chainTail = pair
            }
        }
        return result
    }
}