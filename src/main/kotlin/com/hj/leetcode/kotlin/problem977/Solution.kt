package com.hj.leetcode.kotlin.problem977

/**
 * LeetCode page: [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun sortedSquares(nums: IntArray): IntArray {
        if (0 <= nums[0]) {
            return IntArray(nums.size) { square(nums[it]) }
        }
        if (nums.last() <= 0) {
            return IntArray(nums.size) { square(nums[nums.lastIndex - it]) }
        }

        val result = IntArray(nums.size)
        var right = nums
            .binarySearch(0)
            .let { if (it < 0) -(it + 1) else it }
        var left = right - 1
        var i = 0

        while (0 <= left && right < nums.size) {
            if (nums[left] + nums[right] < 0) {
                result[i] = square(nums[right])
                right++
            } else {
                result[i] = square(nums[left])
                left--
            }
            i++
        }

        while (0 <= left) {
            result[i] = square(nums[left])
            left--
            i++
        }
        while (right < nums.size) {
            result[i] = square(nums[right])
            right++
            i++
        }
        return result
    }

    private fun square(x: Int): Int = x * x
}