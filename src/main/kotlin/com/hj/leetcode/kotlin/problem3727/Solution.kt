package com.hj.leetcode.kotlin.problem3727

import kotlin.math.abs

/**
 * LeetCode page: [3727. Maximum Alternating Sum of Squares](https://leetcode.com/problems/maximum-alternating-sum-of-squares/);
 */
class Solution {
    // Complexity:
    // Time Average O(N) and Space O(N) where N is the length of nums.
    fun maxAlternatingSum(nums: IntArray): Long {
        val n = nums.size
        val absNums = IntArray(n) { abs(nums[it]) }
        quickSelect(absNums, absNums.indices, n / 2)

        var result = 0L
        for (i in 0..<n / 2) {
            result -= absNums[i] * absNums[i]
        }
        for (i in n / 2..<n) {
            result += absNums[i] * absNums[i]
        }
        return result
    }

    // Moves the k smallest values in nums to the front of it.
    private fun quickSelect(
        nums: IntArray,
        indexRange: IntRange,
        k: Int,
    ): Int {
        val pivotIndex = indexRange.random()
        val pivot = nums[pivotIndex]
        var i = indexRange.first
        var j = indexRange.last
        while (i <= j) {
            if (nums[i] < pivot) {
                i++
            } else {
                nums.swap(i, j)
                j--
            }
        }

        val pos = indexRange.first + k
        if (pos < i) {
            return quickSelect(nums, indexRange.first..<i, k)
        }

        j = indexRange.last
        while (i <= j) {
            if (nums[i] == pivot) {
                i++
            } else {
                nums.swap(i, j)
                j--
            }
        }

        return if (pos < i) pivot else quickSelect(nums, i..indexRange.last, pos - i)
    }

    private fun IntArray.swap(
        i: Int,
        j: Int,
    ) {
        this[i] = this[j].also { this[j] = this[i] }
    }
}
