package com.hj.leetcode.kotlin.problem2460

/**
 * LeetCode page: [2460. Apply Operations to an Array](https://leetcode.com/problems/apply-operations-to-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of `nums`.
    fun applyOperations(nums: IntArray): IntArray {
        var tail = 0 // The index in `nums` to place the next nonzero value
        var currValue = 0

        for ((i, nextValue) in nums.withIndex()) {
            nums[i] = 0

            if (currValue == 0) {
                currValue = nextValue
                continue
            }

            if (currValue == nextValue) {
                nums[tail] = currValue * 2
                currValue = 0
            } else {
                nums[tail] = currValue
                currValue = nextValue
            }
            tail++
        }
        if (currValue != 0) {
            nums[tail] = currValue
            tail++
        }
        return nums
    }
}
