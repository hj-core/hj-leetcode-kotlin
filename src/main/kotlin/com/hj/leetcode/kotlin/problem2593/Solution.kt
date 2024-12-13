package com.hj.leetcode.kotlin.problem2593

/**
 * LeetCode page: [2593. Find Score of an Array After Marking All Elements](https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of nums.
     */
    fun findScore(nums: IntArray): Long {
        var result = 0L
        var start = 0 // Starting index of current decision chain

        while (start < nums.size) {
            var end = start
            while (end < nums.lastIndex && nums[end] > nums[end + 1]) {
                end++
            }

            for (i in end downTo start step 2) {
                result += nums[i]
            }
            start = end + 2
        }
        return result
    }
}
