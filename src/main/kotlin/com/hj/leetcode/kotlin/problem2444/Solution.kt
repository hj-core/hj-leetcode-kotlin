package com.hj.leetcode.kotlin.problem2444

import kotlin.math.min

/**
 * LeetCode page: [2444. Count Subarrays With Fixed Bounds](https://leetcode.com/problems/count-subarrays-with-fixed-bounds/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        var result = 0L
        var boundary = -1
        var latestMin = -1
        var latestMax = -1

        for ((end, num) in nums.withIndex()) {
            if (num !in minK..maxK) {
                boundary = end
                continue
            }

            if (num == minK) {
                latestMin = end
            }
            if (num == maxK) {
                latestMax = end
            }

            if (boundary < latestMin && boundary < latestMax) {
                result += min(latestMin, latestMax) - boundary
            }
        }
        return result
    }
}