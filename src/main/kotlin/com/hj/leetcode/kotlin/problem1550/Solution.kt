package com.hj.leetcode.kotlin.problem1550

/**
 * LeetCode page: [1550. Three Consecutive Odds](https://leetcode.com/problems/three-consecutive-odds/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of arr;
     */
    fun threeConsecutiveOdds(arr: IntArray): Boolean {
        var consecutiveOdds = 0
        for (num in arr) {
            consecutiveOdds = if (num.isOdd()) {
                consecutiveOdds + 1
            } else {
                0
            }
            if (consecutiveOdds == 3) {
                return true
            }
        }
        return false
    }

    private fun Int.isOdd(): Boolean = this and 1 == 1
}