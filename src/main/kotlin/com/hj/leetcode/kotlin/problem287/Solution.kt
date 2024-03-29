package com.hj.leetcode.kotlin.problem287

/**
 * LeetCode page: [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(1) where N is the size of nums;
     */
    fun findDuplicate(nums: IntArray): Int {
        var left = 1
        var right = nums.size - 1

        /* Binary search on the range in which the duplicated number located
         * by counting numbers.
         */
        while (left < right) {
            val mid = left + (right - left) / 2
            val inLeftToMid = mid - left + 1 < nums.count { it in left..mid }

            if (inLeftToMid) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}