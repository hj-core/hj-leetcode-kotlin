package com.hj.leetcode.kotlin.problem1658

/**
 * LeetCode page: [1658. Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minOperations(
        nums: IntArray,
        x: Int,
    ): Int {
        var extra = nums.sum() - x // the sum of the remaining part
        if (extra < 0) {
            return -1
        }
        if (extra == 0) {
            return nums.size
        }

        var maxWidth = -1 // the maximum width of the remaining part
        var left = 0
        for (right in nums.indices) {
            extra -= nums[right]
            while (extra < 0) {
                extra += nums[left]
                left++
            }
            if (extra == 0) {
                maxWidth = maxOf(maxWidth, right - left + 1)
            }
        }

        return if (maxWidth == -1) -1 else nums.size - maxWidth
    }
}
