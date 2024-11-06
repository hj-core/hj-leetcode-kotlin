package com.hj.leetcode.kotlin.problem3011

import kotlin.math.max

/**
 * LeetCode page: [3011. Find if Array Can Be Sorted](https://leetcode.com/problems/find-if-array-can-be-sorted/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun canSortArray(nums: IntArray): Boolean {
        // Partition nums into sub arrays whose elements have the same bit count,
        // and check the connections between sub arrays.
        var prevMax = 0
        var currMax = 0
        var currBitCount = 0
        for (num in nums) {
            val bitCount = num.countOneBits()
            if (bitCount != currBitCount) {
                prevMax = currMax
                currBitCount = bitCount
            }

            if (num < prevMax) {
                return false
            }
            currMax = max(currMax, num)
        }
        return true
    }
}
