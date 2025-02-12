package com.hj.leetcode.kotlin.problem2342

/**
 * LeetCode page: [2342. Max Sum of a Pair With Equal Sum of Digits](https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/);
 */
class Solution {
    /* Complexity:
     * Time O(LogP) and Space O(LogM)
     * where P is the product of numbers and M is the maximum number in `nums`.
     */
    fun maximumSum(nums: IntArray): Int {
        var result = -1
        val bestPeers = mutableMapOf<Int, Int>()

        // Pair each nums[j] with the best peer before it and track the maximum sum
        for (j in nums.indices) {
            val digitSum = digitSum(nums[j])
            if (digitSum in bestPeers) {
                val bestPeer = bestPeers[digitSum]!!
                result = maxOf(result, nums[j] + bestPeer)
                bestPeers[digitSum] = maxOf(bestPeer, nums[j])
            } else {
                bestPeers[digitSum] = nums[j]
            }
        }
        return result
    }

    private fun digitSum(x: Int): Int {
        var result = 0
        var left = x
        while (left > 0) {
            result += left % 10
            left /= 10
        }
        return result
    }
}
