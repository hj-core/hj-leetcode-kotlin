package com.hj.leetcode.kotlin.problem2275

/**
 * LeetCode page: [2275. Largest Combination With Bitwise AND Greater Than Zero](https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of candidates.
     */
    fun largestCombination(candidates: IntArray): Int =
        (0..<24) // candidates[i] <= 10^7 < 2^24
            .map { 1 shl it }
            .maxOf { bit ->
                candidates.count { it and bit != 0 }
            }
}
