package com.hj.leetcode.kotlin.problem1503

/**
 * LeetCode page: [1503. Last Moment Before All Ants Fall Out of a Plank](https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1) where N is the size of left and M is the size of right;
     */
    fun getLastMoment(n: Int, left: IntArray, right: IntArray): Int {
        val maxTimeLeft = left.maxOrNull() ?: 0
        val maxTimeRight = right.minOrNull()?.let { n - it } ?: 0
        return maxOf(maxTimeLeft, maxTimeRight)
    }
}