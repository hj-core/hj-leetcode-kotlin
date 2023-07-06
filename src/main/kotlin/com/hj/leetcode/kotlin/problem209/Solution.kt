package com.hj.leetcode.kotlin.problem209

import kotlin.math.min

/**
 * LeetCode page: [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/);
 */
class Solution {

    private val valueIfNoSuchSubArray = 0

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun minSubArrayLen(target: Int, nums: IntArray): Int {

        /* find the minimum length of sub array start at each index and track the
         * minimum among them;
         */
        var result = nums.size + 1
        var end = -1
        var subArraySum = 0
        for (start in nums.indices) {
            subArraySum -= nums.getOrElse(start - 1) { 0 }
            while (subArraySum < target && end < nums.size) {
                end++
                subArraySum += nums.getOrElse(end) { 0 }
            }
            if (end == nums.size) {
                break
            }
            result = minOf(result, end - start + 1)
        }
        return if (result == nums.size + 1) valueIfNoSuchSubArray else result
    }
}