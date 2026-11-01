package com.hj.leetcode.kotlin.problem453

/**
 * LeetCode page: [453. Minimum Moves to Equal Array Elements](https://leetcode.com/problems/minimum-moves-to-equal-array-elements/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minMoves(nums: IntArray): Int {
        var sum = 0L
        var min = nums[0]
        for (num in nums) {
            sum += num
            min = minOf(min, num)
        }
        return (sum - min * nums.size).toInt()
    }
}
