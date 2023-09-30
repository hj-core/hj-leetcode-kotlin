package com.hj.leetcode.kotlin.problem456

/**
 * LeetCode page: [456. 132 Pattern](https://leetcode.com/problems/132-pattern/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun find132pattern(nums: IntArray): Boolean {
        val prefixMin = nums.runningReduce { acc: Int, i: Int -> minOf(acc, i) }
        val stack = mutableListOf<Int>()

        for (index in nums.lastIndex downTo 1) {
            if (nums[index] <= prefixMin[index - 1]) {
                continue
            }

            while (stack.isNotEmpty() && stack.last() <= prefixMin[index - 1]) {
                stack.removeAt(stack.lastIndex)
            }

            if (stack.isNotEmpty() && stack.last() < nums[index]) {
                return true
            }
            stack.add(nums[index])
        }
        return false
    }
}