package com.hj.leetcode.kotlin.problem2090

/**
 * LeetCode page: [2090. K Radius Subarray Averages](https://leetcode.com/problems/k-radius-subarray-averages/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun getAverages(nums: IntArray, k: Int): IntArray {
        if (k == 0) {
            return nums.clone()
        }

        val lessKRadiusAverage = -1
        val result = IntArray(nums.size) { lessKRadiusAverage }

        val validCenterIndices = k until (nums.size - k)
        if (validCenterIndices.isEmpty()) {
            return result
        }

        // Apply sliding window technique
        val windowSize = k * 2 + 1
        var windowSum = nums.sum(0 until windowSize)
        result[k] = (windowSum / windowSize).toInt()

        for (centerIndex in (k + 1) until (nums.size - k)) {
            windowSum = windowSum - nums[centerIndex - k - 1] + nums[centerIndex + k]
            result[centerIndex] = (windowSum / windowSize).toInt()
        }
        return result
    }

    private fun IntArray.sum(indexRange: IntRange): Long {
        return indexRange.fold(0L) { acc: Long, index: Int -> acc + this[index] }
    }
}