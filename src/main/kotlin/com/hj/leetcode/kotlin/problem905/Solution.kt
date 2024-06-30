package com.hj.leetcode.kotlin.problem905

/**
 * LeetCode page: [905. Sort Array By Parity](https://leetcode.com/problems/sort-array-by-parity/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is ths size of nums;
     */
    fun sortArrayByParity(nums: IntArray): IntArray {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            if (nums[left].isEven()) {
                left++
            } else {
                nums.swap(left, right)
                right--
            }
        }
        return nums
    }

    private fun Int.isEven(): Boolean = this and 1 == 0

    private fun IntArray.swap(index: Int, withIndex: Int) {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
    }
}