package com.hj.leetcode.kotlin.problem1963

/**
 * LeetCode page: [1963. Minimum Number of Swaps to Make the String Balanced](https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun minSwaps(s: String): Int =
        when (val count = unbalancedRights(s)) {
            0 -> 0
            1 -> 1
            else -> (count + 1) / 2
        }

    private fun unbalancedRights(s: String): Int {
        var result = 0
        var availableLefts = 0
        for (c in s) {
            when {
                c == '[' -> availableLefts += 1
                availableLefts > 0 -> availableLefts -= 1
                else -> result += 1
            }
        }
        return result
    }
}
