package com.hj.leetcode.kotlin.problem22

/**
 * LeetCode page: [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(4^n * n) and Space O(4^n * n).
    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()
        val len = n * 2
        val builder = CharArray(len)
        for (mask in (1 shl n) - 1..<(1 shl len)) {
            if (mask.countOneBits() != n) {
                continue
            }

            var valid = true
            var closes = 0
            for (shf in 0..<len) {
                val bit = mask shr shf and 1
                closes += bit
                if (closes * 2 > shf + 1) {
                    valid = false
                    break
                }
                builder[shf] = '(' + bit
            }

            if (valid) {
                result.add(String(builder))
            }
        }

        return result
    }
}
