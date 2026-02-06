package com.hj.leetcode.kotlin.problem3634

/**
 * LeetCode page: [3634. Minimum Removals to Balance Array](https://leetcode.com/problems/minimum-removals-to-balance-array/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun minRemoval(
        nums: IntArray,
        k: Int,
    ): Int {
        val nums = nums.sortedArray()

        var maxRetain = 0
        var j = 0
        for (i in nums.indices) {
            val threshold = nums[i].toLong() * k
            while (j < nums.size && nums[j] <= threshold) {
                j++
            }

            maxRetain = maxOf(maxRetain, j - i)
        }

        return nums.size - maxRetain
    }
}
