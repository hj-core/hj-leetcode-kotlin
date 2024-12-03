package com.hj.leetcode.kotlin.problem2109

/**
 * LeetCode page: [2109. Adding Spaces to a String](https://leetcode.com/problems/adding-spaces-to-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun addSpaces(
        s: String,
        spaces: IntArray,
    ): String =
        buildString(s.length + spaces.size) {
            var j = 0 // spaces[j] is the next space to be handled
            for (i in s.indices) {
                if (j < spaces.size && spaces[j] == i) {
                    append(' ')
                    j++
                }
                append(s[i])
            }
        }
}
