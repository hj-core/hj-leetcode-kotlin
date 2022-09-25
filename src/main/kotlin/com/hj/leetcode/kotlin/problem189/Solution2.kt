package com.hj.leetcode.kotlin.problem189

/**
 * LeetCode page: [189. Rotate Array](https://leetcode.com/problems/rotate-array/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun rotate(nums: IntArray, k: Int): Unit {
        val netShift = k % nums.size
        if (netShift == 0) return

        nums.reverse(nums.size - netShift until nums.size)
        nums.reverse(0 until nums.size - netShift)
        nums.reverse()
    }

    private fun IntArray.reverse(range: IntRange = indices) {
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
}