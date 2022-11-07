package com.hj.leetcode.kotlin.problem1323

/**
 * LeetCode page: [1323. Maximum 69 Number](https://leetcode.com/problems/maximum-69-number/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N equals num;
     */
    fun maximum69Number(num: Int): Int {
        require(num in 1..10000)
        var digitUnit = 10000

        while (digitUnit > 0) {
            val digit = (num / digitUnit) % 10
            if (digit == 6) return num + 3 * digitUnit
            digitUnit /= 10
        }
        return num
    }
}