package com.hj.leetcode.kotlin.problem31

/**
 * LeetCode page: [31. Next Permutation](https://leetcode.com/problems/next-permutation/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun nextPermutation(nums: IntArray): Unit {
        val leftIndex = findIndexOfFirstDecreaseFromEnd(nums)
        val decreaseNotFound = leftIndex == -1
        if (decreaseNotFound) {
            nums.reverse(nums.indices)
            return
        }

        val indexSwap = findIndexOfFirstHigherFromEnd(nums[leftIndex], nums, leftIndex + 1)
        nums.swap(leftIndex, indexSwap)
        nums.reverse(leftIndex + 1..nums.lastIndex)
    }

    private fun findIndexOfFirstDecreaseFromEnd(nums: IntArray): Int {
        for (index in nums.lastIndex - 1 downTo 0) {
            if (nums[index] < nums[index + 1]) {
                return index
            }
        }
        return -1
    }

    private fun IntArray.reverse(range: IntRange) {
        var leftIndex = range.first
        var rightIndex = range.last
        while (leftIndex < rightIndex) {
            swap(leftIndex, rightIndex)
            leftIndex++
            rightIndex--
        }
    }

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }

    private fun findIndexOfFirstHigherFromEnd(target: Int, sortedDescending: IntArray, downToIndex: Int): Int {
        val higher = target + 1
        var leftIndex = downToIndex
        var rightIndex = sortedDescending.lastIndex
        while (leftIndex <= rightIndex) {
            val midIndex = (leftIndex + rightIndex) ushr 1
            val mid = sortedDescending[midIndex]
            if (mid >= higher) leftIndex = midIndex + 1 else rightIndex = midIndex - 1
        }

        val higherNotFound = sortedDescending[rightIndex] < higher
        return if (higherNotFound) -1 else rightIndex
    }
}