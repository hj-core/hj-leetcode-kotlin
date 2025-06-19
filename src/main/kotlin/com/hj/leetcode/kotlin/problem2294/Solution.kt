package com.hj.leetcode.kotlin.problem2294

/**
 * LeetCode page: [2294. Partition Array Such That Maximum Difference Is K](https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun partitionArray(
        nums: IntArray,
        k: Int,
    ): Int {
        val sortedNums = nums.sortedArray()
        var result = 1
        var subseqMin = sortedNums[0]
        for (num in sortedNums) {
            if (num > subseqMin + k) {
                result++
                subseqMin = num
            }
        }
        return result
    }
}
