package com.hj.leetcode.kotlin.problem912

class Solution2 {
    /* Complexity:
     * Expected Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun sortArray(nums: IntArray): IntArray {
        quickSort(nums, 0, nums.size)
        return nums
    }

    private fun quickSort(nums: IntArray, start: Int, endExclusive: Int) {
        if (start == endExclusive) {
            return
        }

        val pivotIndex = (start..<endExclusive).random()
        val pivot = nums[pivotIndex]
        // Temporary swap the pivot to the end
        nums[pivotIndex] = nums[endExclusive - 1].also { nums[endExclusive - 1] = pivot }

        var left = start
        var right = endExclusive - 2

        /* Move all numbers that are less than the pivot to the start and then
         * sort those numbers.
         */
        while (left <= right) {
            if (nums[left] < pivot) {
                left++
            } else {
                nums[left] = nums[right].also { nums[right] = nums[left] }
                right--
            }
        }
        quickSort(nums, start, left)

        // Swap pivot to follow the smaller numbers and then sort the remaining
        nums[left] = pivot.also { nums[endExclusive - 1] = nums[left] }
        quickSort(nums, left + 1, endExclusive)
    }
}