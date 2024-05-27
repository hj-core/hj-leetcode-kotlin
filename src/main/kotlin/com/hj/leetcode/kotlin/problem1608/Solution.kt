package com.hj.leetcode.kotlin.problem1608

/**
 * LeetCode page: [1608. Special Array With X Elements Greater Than or Equal X](https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun specialArray(nums: IntArray): Int {
        val sorted = nums.sorted()
        for (i in sorted.indices) {
            val x = sorted.size - i
            if (sorted.getOrElse(i - 1) { 0 } < x && x <= sorted[i]) {
                return x
            }
        }
        return -1
    }
}