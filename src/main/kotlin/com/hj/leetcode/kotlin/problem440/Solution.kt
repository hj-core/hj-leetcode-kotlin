package com.hj.leetcode.kotlin.problem440

/**
 * LeetCode page: [440. K-th Smallest in Lexicographical Order](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/);
 */
class Solution {
    // Complexity:
    // Time O(Log(n)^2) and Space O(Log(n)).
    fun findKthNumber(
        n: Int,
        k: Int,
    ): Int {
        var newK = k
        for (prefix in 1L..<10L) {
            val count = countNumbersWithPrefix(n, prefix)
            if (newK <= count) {
                return findKthNumberWithPrefix(n, newK, prefix)
            }
            newK -= count
        }
        throw IllegalArgumentException("the kth number is out of range")
    }

    // Returns the number of numbers that have the prefix and are not
    // greater than n.
    private fun countNumbersWithPrefix(
        n: Int,
        prefix: Long,
    ): Int {
        var result = 0
        var leftMost = prefix
        var width = 1L
        while (leftMost <= n) {
            result += minOf(width, n + 1L - leftMost).toInt()
            leftMost *= 10
            width *= 10
        }
        return result
    }

    // Returns the kth number that have the prefix and are not greater
    // than n.
    private fun findKthNumberWithPrefix(
        n: Int,
        k: Int,
        prefix: Long,
    ): Int {
        if (k == 1) {
            return prefix.toInt()
        }

        var newK = k - 1
        for (next in 0..<10) {
            val count = countNumbersWithPrefix(n, prefix * 10 + next)
            if (newK <= count) {
                return findKthNumberWithPrefix(n, newK, prefix * 10 + next)
            }
            newK -= count
        }
        throw IllegalArgumentException("the kth number is out of range")
    }
}
