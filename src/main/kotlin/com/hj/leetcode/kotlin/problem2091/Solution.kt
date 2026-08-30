package com.hj.leetcode.kotlin.problem2091

/**
 * LeetCode page: [2091. Removing Minimum and Maximum From Array](https://leetcode.com/problems/removing-minimum-and-maximum-from-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minimumDeletions(nums: IntArray): Int {
        val (minIdx, maxIdx) = indexOfMinAndMax(nums)
        val (left, right) = if (minIdx < maxIdx) Pair(minIdx, maxIdx) else Pair(maxIdx, minIdx)
        return minOf(
            right + 1,
            nums.size - left,
            left + 1 + nums.size - right,
        )
    }

    private fun indexOfMinAndMax(nums: IntArray): Pair<Int, Int> {
        var minIdx = 0
        var minValue = nums[0]
        var maxIdx = 0
        var maxValue = nums[0]
        for (i in 1..<nums.size) {
            if (nums[i] < minValue) {
                minIdx = i
                minValue = nums[i]
            }
            if (nums[i] > maxValue) {
                maxIdx = i
                maxValue = nums[i]
            }
        }
        return Pair(minIdx, maxIdx)
    }
}
