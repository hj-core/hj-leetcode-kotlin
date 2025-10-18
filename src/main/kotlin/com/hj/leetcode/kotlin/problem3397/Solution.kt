package com.hj.leetcode.kotlin.problem3397

/**
 * LeetCode page: [3397. Maximum Number of Distinct Elements After Operations](https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of
    // nums.
    fun maxDistinctElements(
        nums: IntArray,
        k: Int,
    ): Int {
        val sortedNums = nums.sortedArray()
        var result = 0
        var lowerBound = sortedNums[0] - k

        for (num in sortedNums) {
            if (num + k >= lowerBound) {
                result++
                lowerBound = maxOf(lowerBound, num - k) + 1
            }
        }
        return result
    }
}
