package com.hj.leetcode.kotlin.problem2582

/**
 * LeetCode page: [2582. Pass the Pillow](https://leetcode.com/problems/pass-the-pillow/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun passThePillow(n: Int, time: Int): Int {
        val patternLength = 2 * (n - 1)
        val netMoves = time % patternLength
        return 1 + if (netMoves < n) netMoves else (patternLength - netMoves)
    }
}