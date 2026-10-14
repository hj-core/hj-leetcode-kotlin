package com.hj.leetcode.kotlin.problem3723

/**
 * LeetCode page: [3723. Maximize Sum of Squares of Digits](https://leetcode.com/problems/maximize-sum-of-squares-of-digits/);
 */
class Solution {
    // Complexity:
    // Time O(num) and Space O(num).
    fun maxSumOfSquares(
        num: Int,
        sum: Int,
    ): String {
        if (num * 9 < sum) {
            return ""
        }
        val builder = CharArray(num) { '0' }
        val cnt = sum / 9
        builder.fill('9', 0, cnt)
        if (cnt < num) {
            builder[cnt] += sum - cnt * 9
        }
        return String(builder)
    }
}
