package com.hj.leetcode.kotlin.problem35

/**
 * LeetCode page: [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of nums;
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) ushr 1
            val midValue = nums[mid]
            when {
                target < midValue -> right = mid - 1
                target > midValue -> left = mid + 1
                else -> return mid
            }
        }
        return left
    }
}