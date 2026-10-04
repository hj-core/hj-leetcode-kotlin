package com.hj.leetcode.kotlin.problem678

/**
 * LeetCode page: [678. Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun checkValidString(s: String): Boolean {
        var maxNetOpen = 0
        var minNetOpen = 0
        for (c in s) {
            // BIN['(', ')', '*'] = [00101000, 00101001, 00101010]
            val v = c.code shl 1 or c.code

            maxNetOpen += 1 - (v and 1 shl 1)
            if (maxNetOpen < 0) {
                return false
            }

            minNetOpen += 1 - (v and 2)
            minNetOpen = maxOf(minNetOpen, 0)
        }

        return minNetOpen == 0
    }
}
