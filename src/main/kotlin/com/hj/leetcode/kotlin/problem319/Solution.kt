package com.hj.leetcode.kotlin.problem319

/**
 * LeetCode page: [319. Bulb Switcher](https://leetcode.com/problems/bulb-switcher/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun bulbSwitch(n: Int): Int {
        /* After n rounds, only those bulbs at perfect squares positions are on due to
         * their odd number of factors
         */
        return Math.sqrt(n.toDouble()).toInt()
    }
}