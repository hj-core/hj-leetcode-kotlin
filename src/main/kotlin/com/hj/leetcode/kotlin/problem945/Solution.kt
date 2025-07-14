package com.hj.leetcode.kotlin.problem945

/**
 * LeetCode page: [945. Minimum Increment to Make Array Unique](https://leetcode.com/problems/minimum-increment-to-make-array-unique/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun minIncrementForUnique(nums: IntArray): Int {
        val sortedNums = nums.sortedArray()
        var result = 0
        var minPrev = sortedNums[0] - 1
        for (num in sortedNums) {
            minPrev = maxOf(minPrev + 1, num)
            result += minPrev - num
        }
        return result
    }
}
