package com.hj.leetcode.kotlin.problem264

/**
 * LeetCode page: [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun nthUglyNumber(n: Int): Int {
        val uglyNumbers = mutableListOf(1)
        var index2 = 0
        var index3 = 0
        var index5 = 0

        while (uglyNumbers.size < n) {
            val candidate2 = 2 * uglyNumbers[index2]
            val candidate3 = 3 * uglyNumbers[index3]
            val candidate5 = 5 * uglyNumbers[index5]
            val nextUgly = minOf(candidate2, candidate3, candidate5)
            uglyNumbers.add(nextUgly)

            if (candidate2 == nextUgly) {
                index2++
            }
            if (candidate3 == nextUgly) {
                index3++
            }
            if (candidate5 == nextUgly) {
                index5++
            }
        }
        return uglyNumbers.last()
    }
}