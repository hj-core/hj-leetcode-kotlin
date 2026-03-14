package com.hj.leetcode.kotlin.problem1415

/**
 * LeetCode page: [1415. The k-th Lexicographical String of All Happy Strings of Length n](https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(n).
    fun getHappyString(
        n: Int,
        k: Int,
    ): String {
        val k0 = k - 1 // Change from 1-indexed to 0-indexed position
        if (k0 shr (n - 1) > 2) {
            return ""
        }

        var last = k0 shr (n - 1) // index of the last char in "abc"
        val sb = StringBuilder()
        sb.append("abc"[last])

        // transition[ord][last]:= index of the next char
        val transition =
            arrayOf(
                intArrayOf(1, 0, 0),
                intArrayOf(2, 2, 1),
            )

        for (i in (n - 2) downTo 0) {
            val ord = k0 shr i and 1
            last = transition[ord][last]
            sb.append("abc"[last])
        }

        return sb.toString()
    }
}
