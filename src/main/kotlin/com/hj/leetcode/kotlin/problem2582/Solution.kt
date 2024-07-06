package com.hj.leetcode.kotlin.problem2582

/**
 * LeetCode page: [2582. Pass the Pillow](https://leetcode.com/problems/pass-the-pillow/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun passThePillow(n: Int, time: Int): Int {
        // 1 -> (n-1 seconds) -> n -> (n-1 seconds) -> 1 -> ...
        val roundTime = n - 1
        val fullRounds = time / roundTime
        val extraMoves = time - fullRounds * roundTime
        return if (fullRounds % 2 == 0) 1 + extraMoves else n - extraMoves
    }
}