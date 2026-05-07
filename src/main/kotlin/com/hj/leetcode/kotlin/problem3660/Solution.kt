package com.hj.leetcode.kotlin.problem3660

/**
 * LeetCode page: [3660. Jump Game IX](https://leetcode.com/problems/jump-game-ix/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun maxValue(nums: IntArray): IntArray {
        val result = computePrefixMax(nums)
        var suffixMin = result.last()
        for (i in nums.indices.reversed()) {
            if (result[i] > suffixMin) {
                result[i] = result[i + 1]
            }

            if (nums[i] < suffixMin) {
                suffixMin = nums[i]
            }
        }

        return result
    }

    private fun computePrefixMax(nums: IntArray): IntArray {
        val prefixMax = IntArray(nums.size)
        prefixMax[0] = nums[0]
        for (i in 1..<nums.size) {
            prefixMax[i] = maxOf(prefixMax[i - 1], nums[i])
        }
        return prefixMax
    }
}
