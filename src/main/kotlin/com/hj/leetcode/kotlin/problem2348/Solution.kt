package com.hj.leetcode.kotlin.problem2348

/**
 * LeetCode page: [2348. Number of Zero-Filled Subarrays](https://leetcode.com/problems/number-of-zero-filled-subarrays/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun zeroFilledSubarray(nums: IntArray): Long {
        var result = 0L
        var zeroStart = 0
        while (zeroStart < nums.size) {
            if (nums[zeroStart] != 0) {
                zeroStart++
            } else {
                val nextNonZero = findIndexOfNextNonZero(nums, zeroStart)
                val size = nextNonZero - zeroStart
                result += computeNumberOfNonEmptySubarrays(size)
                zeroStart = nextNonZero + 1
            }
        }
        return result
    }

    private fun findIndexOfNextNonZero(nums: IntArray, fromIndex: Int): Int {
        var nextNonZero = fromIndex
        while (nextNonZero < nums.size && nums[nextNonZero] == 0) {
            nextNonZero++
        }
        return nextNonZero
    }

    private fun computeNumberOfNonEmptySubarrays(arraySize: Int): Long {
        return arraySize.toLong() * (arraySize + 1) / 2
    }
}