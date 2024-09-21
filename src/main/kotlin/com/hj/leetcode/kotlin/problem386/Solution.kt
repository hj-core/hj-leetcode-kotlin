package com.hj.leetcode.kotlin.problem386

/**
 * LeetCode page: [386. Lexicographical Numbers](https://leetcode.com/problems/lexicographical-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1).
     */
    fun lexicalOrder(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var value = 1
        for (i in 0..<n) {
            result.add(value)
            if (value * 10 <= n) {
                value *= 10
            } else {
                while (value % 10 == 9 || n <= value) {
                    value /= 10
                }
                value += 1
            }
        }
        return result
    }
}
