package com.hj.leetcode.kotlin.problem2560

/**
 * LeetCode page: [2560. House Robber IV](https://leetcode.com/problems/house-robber-iv/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(1)
    // where N and M are the length and the maximum value of the nums.
    fun minCapability(
        nums: IntArray,
        k: Int,
    ): Int {
        // Binary search on the capability of the robber.
        // The final result is in the range [low, high].
        var low = 1
        var high = nums.max()
        while (low < high) {
            val mid = (low + high) ushr 1
            if (maxRobbedHousesGreedy(nums, mid) < k) {
                low = mid + 1
            } else {
                high = mid
            }
        }
        return low
    }

    private fun maxRobbedHousesGreedy(
        nums: IntArray,
        capability: Int,
    ): Int {
        var result = 0
        var i = 0
        while (i < nums.size) {
            if (nums[i] <= capability) {
                result++
                i += 2
            } else {
                i++
            }
        }
        return result
    }

    private fun maxRobbedHouses(
        nums: IntArray,
        capability: Int,
    ): Int {
        var result = 0
        var streak = 0 // The length of houses in a row that have money no more than capacity
        for (num in nums) {
            if (num <= capability) {
                streak++
            } else {
                result += (streak + 1) / 2
                streak = 0
            }
        }
        result += (streak + 1) / 2
        return result
    }
}
