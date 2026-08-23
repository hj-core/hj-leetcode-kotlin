package com.hj.leetcode.kotlin.problem1927

/**
 * LeetCode page: [1927. Sum Game](https://leetcode.com/problems/sum-game/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of num.
    fun sumGame(num: String): Boolean {
        val n = num.length
        var ds = 0
        var dq = 0

        for (i in 0..<(n / 2)) {
            if (num[i] == '?') {
                dq++
            } else {
                ds += num[i] - '0'
            }
        }
        for (i in (n / 2)..<n) {
            if (num[i] == '?') {
                dq--
            } else {
                ds -= num[i] - '0'
            }
        }

        return dq % 2 != 0 || ds + (dq / 2) * 9 != 0
    }
}
