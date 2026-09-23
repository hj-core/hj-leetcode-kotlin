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
        val sum = nums.sum()
        if (sum < x) {
            return -1
        }

        var maxWidth = -1 // the maximum width of the remaining part
        var shortage = sum - x
        var left = 0
        for (right in nums.indices) {
            shortage -= nums[right]
            while (shortage < 0) {
                shortage += nums[left]
                left++
            }
            if (shortage == 0) {
                maxWidth = maxOf(maxWidth, right - left + 1)
            }
        }

        return if (maxWidth == -1) -1 else nums.size - maxWidth
    }
}
