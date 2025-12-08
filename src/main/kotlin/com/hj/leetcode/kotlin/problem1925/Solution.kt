package com.hj.leetcode.kotlin.problem1925

/**
 * LeetCode page: [1925. Count Square Sum Triples](https://leetcode.com/problems/count-square-sum-triples/);
 */
class Solution {
    // Complexity:
    // Time O(nLog(n)) and Space O(1).
    fun countTriples(n: Int): Int {
        // Euclid's Formula
        var result = 0
        var i = 1
        while (true) {
            val ii = i * i
            if (ii * 2 >= n) {
                break
            }

            var j = i + 1
            while (true) {
                val c = ii + j * j
                if (c > n) {
                    break
                }

                if (gcd(i, j) == 1) {
                    result += n / c
                }
                j += 2
            }
            i += 1
        }
        return result * 2 // We can swap a and b in the triples
    }

    private tailrec fun gcd(
        a: Int,
        b: Int,
    ): Int = if (b == 0) a else gcd(b, a % b)
}
