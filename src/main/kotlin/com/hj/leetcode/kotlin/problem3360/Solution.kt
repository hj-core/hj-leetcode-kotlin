package com.hj.leetcode.kotlin.problem3360

/**
 * LeetCode page: [3360. Stone Removal Game](https://leetcode.com/problems/stone-removal-game/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun canAliceWin(n: Int): Boolean {
        var aliceWin = false
        var n = n
        for (pick in 10 downTo 1) {
            n -= pick
            if (n < 0) {
                break
            }
            aliceWin = !aliceWin
        }
        return aliceWin
    }
}
