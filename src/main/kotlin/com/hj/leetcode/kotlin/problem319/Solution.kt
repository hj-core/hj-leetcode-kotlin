package com.hj.leetcode.kotlin.problem319

/**
 * LeetCode page: [319. Bulb Switcher](https://leetcode.com/problems/bulb-switcher/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun bulbSwitch(n: Int): Int {
        /* After n rounds, each position is toggled its number of factors times, so only the
         * positions of perfect squares are turned on due to their odd number of factors.
         */
        return Math.sqrt(n.toDouble()).toInt()
    }
}