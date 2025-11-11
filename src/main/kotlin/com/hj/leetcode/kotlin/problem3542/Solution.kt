package com.hj.leetcode.kotlin.problem3542

/**
 * LeetCode page: [3542. Minimum Operations to Convert All Elements to Zero](https://leetcode.com/problems/minimum-operations-to-convert-all-elements-to-zero/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun minOperations(nums: IntArray): Int {
        val monoStack = mutableListOf(0)
        var result = 0

        for (num in nums) {
            while (num < monoStack.last()) {
                monoStack.removeLast()
            }
            if (num > monoStack.last()) {
                monoStack.add(num)
                result++
            }
        }
        return result
    }
}
