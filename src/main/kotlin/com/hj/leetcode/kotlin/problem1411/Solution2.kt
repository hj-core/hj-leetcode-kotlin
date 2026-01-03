package com.hj.leetcode.kotlin.problem1411

/**
 * LeetCode page: [1411. Number of Ways to Paint N × 3 Grid](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/);
 */
class Solution2 {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun numOfWays(n: Int): Int {
        // Notes:
        //
        // 1. Recurrent relation
        // numOfWays(1) = 12,
        // numOfWays(2) = 54,
        // numOfWays(n) = 5*numOfWays(n-1) - 2*numOfWays(n-2)
        //
        // 2. Cayley–Hamilton theorem
        // https://en.wikipedia.org/wiki/Cayley%E2%80%93Hamilton_theorem#2_%C3%97_2_matrices

        val modulus = 1_000_000_007

        // base matrix = aM + bI
        // result matrix = cM + dI
        // where M is [[5, -2], [1, 0]] and I is identity(2).
        // By Cayley–Hamilton theorem, M^2 = 5M - 2I
        var a = 1L
        var b = 0L
        var c = 0L
        var d = 1L

        var exp = n - 1
        while (exp > 0) {
            if (exp and 1 == 1) {
                val oldC = c
                c = (5 * a * c + a * d + b * c) % modulus
                d = (b * d - 2 * a * oldC) % modulus
            }

            val oldA = a
            a = (5 * a * a + 2 * a * b) % modulus
            b = (b * b - 2 * oldA * oldA) % modulus
            exp = exp shr 1
        }

        return (c * 54 + d * 12).mod(modulus)
    }
}
