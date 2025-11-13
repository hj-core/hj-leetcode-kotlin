package com.hj.leetcode.kotlin.problem3228

/**
 * LeetCode page: [3228. Maximum Number of Operations to Move Ones to the End](https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun maxOperations(s: String): Int {
        var result = 0
        var gaps = 0 // 1-0 transitions
        for (i in s.length - 2 downTo 0) {
            if (s[i] == '1') {
                gaps += '1' - s[i + 1]
                result += gaps
            }
        }
        return result
    }
}
