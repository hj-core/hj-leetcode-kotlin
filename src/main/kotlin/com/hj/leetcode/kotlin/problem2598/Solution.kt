package com.hj.leetcode.kotlin.problem2598

/**
 * LeetCode page: [2598. Smallest Missing Non-negative Integer After Operations](https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations/);
 */
class Solution {
    // Complexity:
    // Time O(N+value) and Space O(value) where N is the
    // length of nums.
    fun findSmallestInteger(
        nums: IntArray,
        value: Int,
    ): Int {
        val modFreq = IntArray(value)
        for (num in nums) {
            modFreq[num.mod(value)]++
        }

        return (0..<value)
            .minBy(modFreq::get)
            .let { it + value * modFreq[it] }
    }
}
