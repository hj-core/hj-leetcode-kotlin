package com.hj.leetcode.kotlin.problem2444

import kotlin.math.min

/**
 * LeetCode page: [2444. Count Subarrays With Fixed Bounds](https://leetcode.com/problems/count-subarrays-with-fixed-bounds/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countSubarrays(
        nums: IntArray,
        minK: Int,
        maxK: Int,
    ): Long {
        // We consider the number of fixed-bound subarrays for each subarray end index,
        // where the range of valid start indices is determined by the index of the last
        // out-of-bounds element, as well as the last occurrences of minK and maxK.
        var result = 0L
        var leftBound = -1
        var latestMin = -1
        var latestMax = -1

        for ((right, num) in nums.withIndex()) {
            if (num !in minK..maxK) {
                leftBound = right
                continue
            }

            if (num == minK) {
                latestMin = right
            }
            if (num == maxK) {
                latestMax = right
            }

            if (leftBound < latestMin && leftBound < latestMax) {
                result += min(latestMin, latestMax) - leftBound
            }
        }
        return result
    }
}
