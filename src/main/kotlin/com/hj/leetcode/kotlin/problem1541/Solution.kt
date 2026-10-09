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
            val v = c.code and 1
            netOpen += 1 - (v shl 1) + (v and count)
            added += (netOpen ushr 31) + (v xor count and count)
            netOpen += netOpen ushr 31
            count = v xor count and v
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
