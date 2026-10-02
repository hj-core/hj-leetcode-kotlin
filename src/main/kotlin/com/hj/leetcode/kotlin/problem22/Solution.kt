package com.hj.leetcode.kotlin.problem22

/**
 * LeetCode page: [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(comb(2n, n) * n) and Space O(comb(2n, n) * n).
    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()
        val len = n * 2
        val builder = CharArray(len)
        var mask = (1 shl n) - 1
        while (mask < 1 shl len) {
            if (fillSequence(mask, builder)) {
                result.add(String(builder))
            }
            mask = gospersHack(mask)
        }
        return result
    }

    private fun fillSequence(
        mask: Int,
        out: CharArray,
    ): Boolean {
        var netOpen = 0
        for (shf in out.indices) {
            val bit = mask shr shf and 1
            netOpen += 1 - (bit shl 1)
            if (netOpen < 0) {
                return false
            }
            out[shf] = '(' + bit
        }
        return netOpen == 0
    }

    private fun gospersHack(x: Int): Int {
        val c = x and -x
        val r = x + c
        val s = 2 + c.countTrailingZeroBits()
        return (r xor x) ushr s or r
    }
}
