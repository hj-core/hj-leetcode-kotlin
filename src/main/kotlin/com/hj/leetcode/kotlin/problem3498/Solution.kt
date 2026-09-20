package com.hj.leetcode.kotlin.problem3498

/**
 * LeetCode page: [3498. Reverse Degree of a String](https://leetcode.com/problems/reverse-degree-of-a-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun reverseDegree(s: String): Int {
        val z1 = 'z' + 1
        var ans = 0
        var run = 0
        for (i in s.indices.reversed()) {
            run += z1 - s[i]
            ans += run
        }
        return ans
    }

    //  fun reverseDegree(s: String): Int {
    //      val z1 = 'z' + 1
    //      return s.foldIndexed(0) { index, acc, ch -> acc + (index + 1) * (z1 - ch) }
    //  }
}
