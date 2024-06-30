package com.hj.leetcode.kotlin.problem338

/**
 * LeetCode page: [338. Counting Bits](https://leetcode.com/problems/counting-bits/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun countBits(n: Int): IntArray {
        val result = IntArray(n + 1)
        for (number in 1..n) {
            result[number] = result[number shr 1] + (number and 1)
        }
        return result
    }
}