package com.hj.leetcode.kotlin.problem704

/**
 * LeetCode page: [704. Binary Search](https://leetcode.com/problems/binary-search/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(1) where N is the size of nums;
     */
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = (left + right) ushr 1
            val midValue = nums[mid]
            when {
                midValue < target -> left = mid + 1
                midValue > target -> right = mid - 1
                else -> return mid
            }
        }
        return -1
    }
}