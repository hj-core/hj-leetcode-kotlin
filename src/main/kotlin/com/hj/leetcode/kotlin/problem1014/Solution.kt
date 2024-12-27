package com.hj.leetcode.kotlin.problem1014

/**
 * LeetCode page: [1014. Best Sightseeing Pair](https://leetcode.com/problems/best-sightseeing-pair/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of values.
     */
    fun maxScoreSightseeingPair(values: IntArray): Int {
        var result = values[1] - 1 + values[0]
        var maxBefore = values[0] + 0
        for (j in 1..<values.size) {
            result = maxOf(result, values[j] - j + maxBefore)
            maxBefore = maxOf(maxBefore, values[j] + j)
        }
        return result
    }
}
