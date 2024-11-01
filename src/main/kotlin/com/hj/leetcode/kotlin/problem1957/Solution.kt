package com.hj.leetcode.kotlin.problem1957

/**
 * LeetCode page: [1957. Delete Characters to Make Fancy String](https://leetcode.com/problems/delete-characters-to-make-fancy-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the length of s.
     */
    fun makeFancyString(s: String): String =
        buildString {
            var streak = 0
            var prev: Char? = null

            for (c in s) {
                streak = if (c == prev) streak + 1 else 1
                prev = c

                if (streak == 3) {
                    streak -= 1
                } else {
                    append(c)
                }
            }
        }
}
