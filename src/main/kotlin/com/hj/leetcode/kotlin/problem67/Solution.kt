package com.hj.leetcode.kotlin.problem67

/**
 * LeetCode page: [67. Add Binary](https://leetcode.com/problems/add-binary/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the longer length of a and b;
     */
    fun addBinary(a: String, b: String): String {
        val (long, short) = if (a.length > b.length) a to b else b to a
        val reversedRes = StringBuilder()
        var carry = 0

        for (i in long.indices) {
            val bit1 = long.let { it[it.lastIndex - i].digitToInt() }
            val bit2 = short.let { it.getOrNull(it.lastIndex - i)?.digitToInt() } ?: 0
            val sum = bit1 + bit2 + carry
            reversedRes.append(sum and 1)
            carry = if (sum < 2) 0 else 1
        }
        if (carry == 1) reversedRes.append(carry)

        return reversedRes.run {
            reverse()
            toString()
        }
    }

    private fun Char.digitToInt(): Int {
        val res = Character.digit(this.toInt(), 10)
        if (res < 0) throw IllegalArgumentException("Char $this is not a decimal digit")
        return res
    }
}