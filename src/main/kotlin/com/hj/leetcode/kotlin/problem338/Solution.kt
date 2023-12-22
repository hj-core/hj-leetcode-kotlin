package com.hj.leetcode.kotlin.problem338

/**
 * LeetCode page: [338. Counting Bits](https://leetcode.com/problems/counting-bits/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun countBits(n: Int): IntArray {
        val result = IntArray(n + 1)
        var msb = 1

        for (number in 1..n) {
            val difference = number - msb
            if (difference < msb) {
                result[number] = 1 + result[difference]
            } else {
                result[number] = 1
                msb *= 2
            }
        }
        return result
    }
}