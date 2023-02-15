package com.hj.leetcode.kotlin.problem989

/**
 * LeetCode page: [989. Add to Array-Form of Integer](https://leetcode.com/problems/add-to-array-form-of-integer/);
 */
class Solution {
    /* Complexity:
     * Time O(num.size+Log(k)) and Space O(num.size+Log(k));
     */
    fun addToArrayForm(num: IntArray, k: Int): List<Int> {
        val kDigits = k.digits()
        val reversedResult = mutableListOf<Int>()
        var carry = 0
        val maxSize = maxOf(num.size, kDigits.size)

        for (i in 0 until maxSize) {
            val digit1 = num.let { it.getOrNull(it.lastIndex - i) ?: 0 }
            val digit2 = kDigits.let { it.getOrNull(it.lastIndex - i) ?: 0 }
            val sum = digit1 + digit2 + carry
            reversedResult.add(sum % 10)
            carry = if (sum < 10) 0 else 1
        }
        if (carry == 1) reversedResult.add(carry)
        return reversedResult.apply { reverse() }
    }

    private fun Int.digits(): List<Int> {
        if (this == 0) return listOf(0)

        val reversedDigits = mutableListOf<Int>()
        var num = Math.abs(this)
        while (num != 0) {
            reversedDigits.add(num % 10)
            num /= 10
        }
        return reversedDigits.apply { reverse() }
    }
}