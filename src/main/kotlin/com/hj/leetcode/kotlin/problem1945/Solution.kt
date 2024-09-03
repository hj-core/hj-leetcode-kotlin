package com.hj.leetcode.kotlin.problem1945

/**
 * LeetCode page: [1945. Sum of Digits of String After Convert](https://leetcode.com/problems/sum-of-digits-of-string-after-convert/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun getLucky(
        s: String,
        k: Int,
    ): Int {
        var result = firstTransform(s)
        for (round in 2..k) {
            result = sumOfDigits(result)
            if (result < 10) break
        }
        return result
    }

    private fun firstTransform(s: String): Int {
        var result = 0
        for (c in s) {
            result += sumOfDigits(c - 'a' + 1)
        }
        return result
    }

    private fun sumOfDigits(num: Int): Int {
        require(0 <= num)
        var result = 0
        var x = num
        while (x > 0) {
            result += x % 10
            x /= 10
        }
        return result
    }
}
