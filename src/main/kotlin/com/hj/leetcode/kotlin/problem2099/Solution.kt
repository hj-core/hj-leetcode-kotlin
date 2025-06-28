package com.hj.leetcode.kotlin.problem2099

/**
 * LeetCode page: [2099. Find Subsequence of Length K With the Largest Sum](https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/);
 */
class Solution {
    // Complexity:
    // Time expected O(N) and Space O(N) where N is the length of nums.
    fun maxSubsequence(
        nums: IntArray,
        k: Int,
    ): IntArray {
        val numsClone = nums.clone()
        val kthLargest = quickSelect(numsClone, k, 0, numsClone.size)
        var kthLargestNeeded = ((numsClone.size - k)..<numsClone.size).count { numsClone[it] == kthLargest }

        val result = IntArray(k)
        var i = 0
        for (num in nums) {
            if (num > kthLargest) {
                result[i] = num
                i++
            } else if (num == kthLargest && kthLargestNeeded > 0) {
                result[i] = num
                i++
                kthLargestNeeded--
            }
        }
        return result
    }

    // quickSelect modifies the nums[start..<end] in-place so that
    // the k largest elements in that index range are placed at the
    // end of the range.
    //
    // The returned value is the kth largest element in the range.
    private fun quickSelect(
        nums: IntArray,
        k: Int,
        start: Int,
        end: Int, // Exclusive
    ): Int {
        require(0 <= start && start < end && end <= nums.size) {
            "Invalid or empty range"
        }

        val width = end - start
        require(k <= width) {
            "There are less than k elements in the given range"
        }
        if (width == 1) {
            return nums[start]
        }

        val pivot = (start..<end).random()
        val pivotValue = nums[pivot]

        // nums[start..<left] < pivotValue and nums[right+1..<end] >= pivotValue
        nums.swap(pivot, end - 1)
        var left = start
        var right = end - 2

        while (left <= right) {
            if (nums[left] < pivotValue) {
                left++
            } else {
                nums.swap(left, right)
                right--
            }
        }
        nums.swap(left, end - 1)

        val diff = k - (end - left)
        return when {
            diff < 0 -> quickSelect(nums, k, left + 1, end)
            diff > 0 -> quickSelect(nums, diff, start, left)
            else -> nums[left]
        }
    }

    private fun IntArray.swap(
        index: Int,
        withIndex: Int,
    ) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }
}
