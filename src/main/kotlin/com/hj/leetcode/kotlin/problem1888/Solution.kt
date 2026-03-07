package com.hj.leetcode.kotlin.problem1888

/**
 * LeetCode page: [1888. Minimum Number of Flips to Make the Binary String Alternating](https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun minFlips(s: String): Int =
        if (s.length and 1 == 0) {
            minFlipsEvenLen(s)
        } else {
            minFlipsOddLen(s)
        }

    private fun minFlipsEvenLen(s: String): Int {
        var p1 = 0 // targeting a 0101... pattern
        for ((i, s) in s.withIndex()) {
            p1 += (s.code xor i) and 1
        }
        return minOf(p1, s.length - p1)
    }

    private fun minFlipsOddLen(s: String): Int {
        // rotates-then-flips, initial value shift by p1 + 1
        var minP3 = -1
        var maxP3 = -1
        var p1 = 0
        for ((i, s) in s.withIndex()) {
            p1 += (s.code xor i) and 1
            minP3 = minOf(minP3, i - 2 * p1)
            maxP3 = maxOf(maxP3, i - 2 * p1)
        }
        minP3 += p1 + 1
        maxP3 += p1 + 1
        return minOf(minP3, s.length - maxP3)
    }
}
