package com.hj.leetcode.kotlin.problem1871

/**
 * LeetCode page: [1871. Jump Game VII](https://leetcode.com/problems/jump-game-vii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun canReach(
        s: String,
        minJump: Int,
        maxJump: Int,
    ): Boolean {
        if (s.last() != '0') {
            return false
        }

        val n = s.length
        val line = IntArray(n + 1)
        line[0]++
        line[1]--

        var sweep = 0
        for (i in 0 until n - minJump) {
            sweep += line[i]
            if (sweep > 0 && s[i] == '0') {
                line[minOf(i + minJump, n)]++
                line[minOf(i + maxJump + 1, n)]--
                if (line[n] < 0) {
                    return true
                }
            }
        }

        return false
    }
}
