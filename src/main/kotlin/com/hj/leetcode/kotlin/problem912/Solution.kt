package com.hj.leetcode.kotlin.problem912

/**
 * LeetCode page: [912. Sort an Array](https://leetcode.com/problems/sort-an-array/);
 *
 * TODO : Add Heap Sort solution;
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun sortArray(nums: IntArray): IntArray {
        mergeSort(nums, 0, nums.size, IntArray(nums.size))
        return nums
    }

    private fun mergeSort(
        nums: IntArray,
        start: Int,
        endExclusive: Int,
        tempResult: IntArray,
    ) {
        if (start + 1 == endExclusive) {
            return
        }
        val mid = (start + endExclusive) ushr 1
        mergeSort(nums, start, mid, tempResult)
        mergeSort(nums, mid, endExclusive, tempResult)
        merge(nums, start, endExclusive, tempResult)
    }

    private fun merge(
        nums: IntArray,
        start: Int,
        endExclusive: Int,
        tempResult: IntArray,
    ) {
        val mid = (start + endExclusive) ushr 1
        var left = start
        var right = mid
        var i = start

        // Merge the two sorted arrays in range [start, mid) and [mid, endExclusive)
        while (left < mid && right < endExclusive) {
            if (nums[left] <= nums[right]) {
                tempResult[i] = nums[left]
                left++
            } else {
                tempResult[i] = nums[right]
                right++
            }
            i++
        }

        while (left < mid) {
            tempResult[i] = nums[left]
            left++
            i++
        }
        while (right < endExclusive) {
            tempResult[i] = nums[right]
            right++
            i++
        }

        // Copy the tempResult to nums
        for (j in start..<endExclusive) {
            nums[j] = tempResult[j]
        }
    }
}