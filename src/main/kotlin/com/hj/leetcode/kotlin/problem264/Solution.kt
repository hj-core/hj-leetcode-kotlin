package com.hj.leetcode.kotlin.problem264

/**
 * LeetCode page: [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N equals n;
     */
    fun nthUglyNumber(n: Int): Int {
        val uglyNumbers = mutableListOf(1)

        var index2 = 0
        var candidate2 = 2 * uglyNumbers[index2]

        var index3 = 0
        var candidate3 = 3 * uglyNumbers[index3]

        var index5 = 0
        var candidate5 = 5 * uglyNumbers[index5]

        while (uglyNumbers.size < n) {
            val nextUgly = minOf(candidate2, candidate3, candidate5)
            uglyNumbers.add(nextUgly)

            if (candidate2 == nextUgly) {
                index2++
                candidate2 = 2 * uglyNumbers[index2]
            }

            if (candidate3 == nextUgly) {
                index3++
                candidate3 = 3 * uglyNumbers[index3]
            }

            if (candidate5 == nextUgly) {
                index5++
                candidate5 = 5 * uglyNumbers[index5]
            }
        }

        return uglyNumbers.last()
    }
}