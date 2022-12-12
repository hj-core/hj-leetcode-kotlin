package com.hj.leetcode.kotlin.problem70

/**
 * LeetCode page: [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/description/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun climbStairs(n: Int): Int {
        var waysToPrevStep = 1
        var waysToCurrStep = 1
        repeat(n - 1) {
            val waysToNextStep = waysToPrevStep + waysToCurrStep
            waysToPrevStep = waysToCurrStep
            waysToCurrStep = waysToNextStep
        }
        return waysToCurrStep
    }
}