package com.hj.leetcode.kotlin.problem2914

/**
 * LeetCode page: [2914. Minimum Number of Changes to Make Binary String Beautiful](https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun minChanges(s: String): Int = (s.indices step 2).count { s[it] != s[it + 1] }
}
