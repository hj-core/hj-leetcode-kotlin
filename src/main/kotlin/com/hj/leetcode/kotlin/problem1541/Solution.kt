package com.hj.leetcode.kotlin.problem1541

/**
 * LeetCode page: [1541. Minimum Insertions to Balance a Parentheses String](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun minInsertions(s: String): Int {
        var added = 0
        var netOpen = 0
        var count = 0 // 1 if a ')' has not yet found the second ')'
        for (c in s) {
            // +-------------+--------------+--------------+
            // | c&1 \ count | 0            | 1            |
            // +-------------+--------------+--------------+
            // | 0           | added        | added++      |
            // |             | count = 0    | count = 0    |
            // |             | netOpen++    | netOpen++    |
            // +-------------+--------------+--------------+
            // | 1           | added        | added        |
            // |             | count = 1    | count = 0    |
            // |             | netOpen--    | netOpen      |
            // +-------------+--------------+--------------+
            val v = c.code and 1 xor count
            added += count and v
            count = c.code and v
            netOpen += 1 - count - (c.code and 1)
            added += netOpen ushr 31
            netOpen += netOpen ushr 31
        }
        added += netOpen * 2 + count
        return added
    }

    //  fun minInsertions(s: String): Int {
    //      var added = 0
    //      var netOpen = 0
    //      var i = 0
    //      while (i < s.length) {
    //          if (s[i] == '(') {
    //              netOpen++
    //              i++
    //          } else {
    //              i++
    //              if (i < s.length && s[i] == ')') {
    //                  i++
    //              } else {
    //                  added++
    //              }
    //
    //              netOpen--
    //              if (netOpen < 0) {
    //                  added++
    //                  netOpen = 0
    //              }
    //          }
    //      }
    //
    //      added += netOpen * 2
    //      return added
    //  }
}
