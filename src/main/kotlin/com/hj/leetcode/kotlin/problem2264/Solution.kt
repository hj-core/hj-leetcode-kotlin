package com.hj.leetcode.kotlin.problem2264

/**
 * LeetCode page: [2264. Largest 3-Same-Digit Number in String](https://leetcode.com/problems/largest-3-same-digit-number-in-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of num.
    fun largestGoodInteger(num: String): String {
        var maxDigit = '0' - 1
        var i = 0
        while (i < num.length - 2) {
            if (num[i] == num[i + 1] && num[i + 1] == num[i + 2]) {
                maxDigit = maxOf(maxDigit, num[i + 2])
                i += 3
            } else {
                i += 1
            }
        }
        return if (maxDigit < '0') "" else "$maxDigit$maxDigit$maxDigit"
    }
}
