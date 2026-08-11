package com.hj.leetcode.kotlin.problem2996

/**
 * LeetCode page: [2996. Smallest Missing Integer Greater Than Sequential Prefix Sum](https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun missingInteger(nums: IntArray): Int {
        var sum = nums[0]
        var seen = 1L shl nums[0]
        var i = 1
        while (i < nums.size && nums[i] == nums[i - 1] + 1) {
            sum += nums[i]
            seen = 1L shl nums[i] or seen
            i++
        }
        for (i in i..<nums.size) {
            seen = 1L shl nums[i] or seen
        }

        seen = seen shr minOf(sum, 63)
        while (seen and 1L > 0L) {
            sum++
            seen = seen shr 1
        }

        return sum
    }
}
