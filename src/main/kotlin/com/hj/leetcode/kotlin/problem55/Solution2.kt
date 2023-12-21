package com.hj.leetcode.kotlin.problem55

/**
 * LeetCode page: [55. Jump Game](https://leetcode.com/problems/jump-game/);
 */
class Solution2 {
    /* Complexity:
     * Time O(|nums|) and Space O(1);
     */
    fun canJump(nums: IntArray): Boolean {
        var maxReachable = 0
        for (index in nums.indices) {
            if (index > maxReachable) return false
            val furthestJump = index + nums[index]
            maxReachable = maxOf(maxReachable, furthestJump)
        }
        return maxReachable >= nums.lastIndex
    }
}