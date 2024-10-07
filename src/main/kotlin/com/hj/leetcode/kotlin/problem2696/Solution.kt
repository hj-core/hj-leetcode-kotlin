package com.hj.leetcode.kotlin.problem2696

/**
 * LeetCode page: [2696. Minimum String Length After Removing Substrings](https://leetcode.com/problems/minimum-string-length-after-removing-substrings/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s.
     */
    fun minLength(s: String): Int {
        val stack = mutableListOf<Char>()
        for (c in s) {
            when {
                stack.isEmpty() -> stack.add(c)
                c == 'B' && stack.last() == 'A' -> stack.removeLast()
                c == 'D' && stack.last() == 'C' -> stack.removeLast()
                else -> stack.add(c)
            }
        }
        return stack.size
    }
}
