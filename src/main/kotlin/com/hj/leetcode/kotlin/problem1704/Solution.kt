package com.hj.leetcode.kotlin.problem1704

/**
 * LeetCode page: [1704. Determine if String Halves Are Alike](https://leetcode.com/problems/determine-if-string-halves-are-alike/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|) and Space O(|s|);
     */
    fun halvesAreAlike(s: String): Boolean {
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        var counter = 0
        val halfSize = s.length shr 1

        for (index in 0 until halfSize) {
            val char = s[index]
            if (char in vowels) counter++
        }

        for (index in halfSize until s.length) {
            val char = s[index]
            if (char in vowels) counter--
        }

        return counter == 0
    }
}