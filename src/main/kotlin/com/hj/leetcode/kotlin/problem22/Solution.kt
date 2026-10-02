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
        var mask = (1 shl n) - 1
        while (mask < 1 shl len) {
            val ones = mask.countOneBits()
            if (ones == n) {
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
            mask += if (ones < n) 1 else mask.takeLowestOneBit()
        }

        return result
    }
}
