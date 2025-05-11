package com.hj.leetcode.kotlin.problem1550

/**
 * LeetCode page: [1550. Three Consecutive Odds](https://leetcode.com/problems/three-consecutive-odds/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of arr;
     */
    fun threeConsecutiveOdds(arr: IntArray): Boolean {
        var pendingLen = 3
        for (num in arr) {
            if (num and 1 == 0) {
                pendingLen = 3
                continue
            }

            pendingLen--
            if (pendingLen == 0) {
                return true
            }
        }
        return false
    }
}
