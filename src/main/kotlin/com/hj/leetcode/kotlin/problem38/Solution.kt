package com.hj.leetcode.kotlin.problem38

/**
 * LeetCode page: [38. Count and Say](https://leetcode.com/problems/count-and-say/);
 */
class Solution {
    // Complexity:
    // Time O(2^n) and Space O(2^n).
    fun countAndSay(n: Int): String {
        val digits = ArrayDeque<Int>(1 shl (n * 4 / 10))
        digits.add(1)

        repeat(n - 1) {
            countAndSayNext(digits)
        }
        return digits.joinToString("")
    }

    private fun countAndSayNext(digits: ArrayDeque<Int>) {
        var count = 0
        var prev = digits[0]

        repeat(digits.size) {
            if (digits[0] == prev) {
                count++
            } else {
                digits.addLast(count)
                digits.addLast(prev)
                count = 1
                prev = digits[0]
            }

            digits.removeFirst()
        }
        digits.addLast(count)
        digits.addLast(prev)
    }
}
