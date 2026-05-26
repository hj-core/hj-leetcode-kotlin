package com.hj.leetcode.kotlin.problem1871

/**
 * LeetCode page: [1871. Jump Game VII](https://leetcode.com/problems/jump-game-vii/);
 */
class Solution2 {
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

        // Derive from the sweep-line algorithm, mapping range to source (one-to-one mapping).
        val n = s.length
        val isReachable = BooleanArray(n)
        isReachable[0] = s[0] == '0'

        var sweep = 0
        for (i in minJump until n) {
            if (isReachable[i - minJump]) {
                sweep++
            }
            if (sweep > 0 && s[i] == '0') {
                isReachable[i] = true
            }
            if (i >= maxJump && isReachable[i - maxJump]) {
                sweep--
            }
        }

        return isReachable[n - 1]
    }
}
