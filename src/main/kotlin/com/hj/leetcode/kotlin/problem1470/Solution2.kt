package com.hj.leetcode.kotlin.problem1470

/**
 * LeetCode page: [1470. Shuffle the Array](https://leetcode.com/problems/shuffle-the-array/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun shuffle(nums: IntArray, n: Int): IntArray {
        require(nums.size == n * 2)

        shuffleButNegativeValues(nums, n)
        restoreValuesBackToPositive(nums)
        return nums
    }

    /**
     * Require [nums] contains positive values only.
     */
    private fun shuffleButNegativeValues(nums: IntArray, n: Int) {
        for (i in nums.indices) {
            var finalIndex = getFinalIndex(i, n)
            while (nums[i] > 0) {
                nums[i] = -nums[i]
                nums.swap(i, finalIndex)
                finalIndex = getFinalIndex(finalIndex, n)
            }
        }
    }

    private fun getFinalIndex(initialIndex: Int, n: Int): Int {
        return if (initialIndex < n) initialIndex * 2 else (initialIndex - n) * 2 + 1
    }

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }

    private fun restoreValuesBackToPositive(nums: IntArray) {
        for (i in nums.indices) {
            nums[i] = -nums[i]
        }
    }
}