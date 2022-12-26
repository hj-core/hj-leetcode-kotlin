package com.hj.leetcode.kotlin.problem55

/**
 * LeetCode page: [55. Jump Game](https://leetcode.com/problems/jump-game/);
 */
class Solution {
    /* Complexity:
     * Time O(|nums|) and Space O(1);
     */
    fun canJump(nums: IntArray): Boolean {
        var firstReachable = nums.lastIndex
        for (index in nums.indices.reversed()) {
            val furthestJump = index + nums[index]
            if (furthestJump >= firstReachable) {
                firstReachable = index
            }
        }
        return firstReachable == 0
    }
}