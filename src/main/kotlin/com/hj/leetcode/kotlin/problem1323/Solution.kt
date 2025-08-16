package com.hj.leetcode.kotlin.problem1323

/**
 * LeetCode page: [1323. Maximum 69 Number](https://leetcode.com/problems/maximum-69-number/);
 */
class Solution {
    // Complexity:
    // Time O(Log M) and Space O(1) where M is the upper bound
    // of num.
    fun maximum69Number(num: Int): Int {
        var unit = 3000
        while (unit > 0) {
            val f = num / unit
            if (f > 0 && f and 1 == 0) {
                return num + unit
            }
            unit /= 10
        }
        return num
    }
}
