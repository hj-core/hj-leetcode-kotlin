package com.hj.leetcode.kotlin.problem189

/**
 * LeetCode page: [189. Rotate Array](https://leetcode.com/problems/rotate-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun rotate(nums: IntArray, k: Int): Unit {
        nums.rotate(nums.indices, k)
    }

    private tailrec fun IntArray.rotate(range: IntRange, shift: Int) {
        val size = range.last - range.first + 1
        val netShift = shift % size
        if (netShift == 0) return

        for (index in range.last downTo range.first + netShift) {
            swap(index, index - netShift)
        }

        val rem = size % netShift
        val hasFullyRotated = rem == 0
        if (hasFullyRotated) return

        val pendingRange = range.first until range.first + netShift
        val pendingShift = netShift - rem
        rotate(pendingRange, pendingShift)
    }

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }
}