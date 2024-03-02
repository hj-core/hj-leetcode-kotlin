package com.hj.leetcode.kotlin.problem977

/**
 * LeetCode page: [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun sortedSquares(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var left = 0
        var right = nums.lastIndex
        var i = nums.lastIndex

        while (left <= right) {
            if (nums[left] + nums[right] < 0) {
                result[i] = square(nums[left])
                left++
            } else {
                result[i] = square(nums[right])
                right--
            }
            i--
        }
        return result
    }

    private fun square(x: Int): Int = x * x
}