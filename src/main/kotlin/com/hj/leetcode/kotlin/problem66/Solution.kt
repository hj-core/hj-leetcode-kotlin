package com.hj.leetcode.kotlin.problem66

/**
 * LeetCode page: [66. Plus One](https://leetcode.com/problems/plus-one/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of digits.
    fun plusOne(digits: IntArray): IntArray {
        if (digits.all { it == 9 }) {
            return IntArray(digits.size + 1).also {
                it[0] = 1
            }
        }

        val result = digits.clone()
        for (i in digits.indices.reversed()) {
            val sum = digits[i] + 1
            if (sum < 10) {
                result[i] = sum
                break
            } else {
                result[i] = sum - 10
            }
        }

        return result
    }
}
