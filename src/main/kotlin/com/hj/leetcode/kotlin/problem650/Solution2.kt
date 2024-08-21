package com.hj.leetcode.kotlin.problem650

import kotlin.math.sqrt

/**
 * LeetCode page: [650. 2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard/);
 */
class Solution2 {
    /* Complexity:
     * Time O(sqrt(n)) and Space O(1);
     */
    fun minSteps(n: Int): Int {
        // Hints: Op(1, CPPPCPPPP)=1x4x5=20 and p+q<=p*q for p,q>1
        var result = 0
        var num = n
        var d = 2
        val capD = sqrt(n.toFloat()) + 1 // Skip the test of primes after sqrt(n)
        while (d <= num && d <= capD) {
            while (num % d == 0) {
                num /= d
                result += d
            }
            d += 1
        }

        return when (num) {
            1 -> result
            n -> n
            else -> result + num
        }
    }
}
