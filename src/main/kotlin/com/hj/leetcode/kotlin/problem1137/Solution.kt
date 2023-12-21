package com.hj.leetcode.kotlin.problem1137

/**
 * LeetCode page: [1137. N-th Tribonacci Number](https://leetcode.com/problems/n-th-tribonacci-number/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun tribonacci(n: Int): Int {
        var first = 0
        var second = 1
        var third = 1

        repeat(n) {
            val next = first + second + third
            first = second
            second = third
            third = next
        }
        return first
    }
}
