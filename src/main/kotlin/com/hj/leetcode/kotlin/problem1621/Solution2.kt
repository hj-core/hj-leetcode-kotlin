package com.hj.leetcode.kotlin.problem1621

/**
 * LeetCode page: [1621. Number of Sets of K Non-Overlapping Line Segments](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/);
 */
class Solution2 {
    // Complexity:
    // Time O(k + LogM) and Space O(1) where M is 1_000_000_007.
    fun numberOfSets(
        n: Int,
        k: Int,
    ): Int = combinationMod(n + k - 1, 2 * k, 1_000_000_007)

    // Returns nCr % modulo.
    private fun combinationMod(
        n: Int,
        r: Int,
        modulo: Int,
    ): Int {
        val a = productMod(n - r + 1, n, modulo)
        val b = productMod(1, r, modulo)
        val bInv = quickPow(b, modulo - 2, modulo) // Fermat's little theorem
        return (a * bInv).mod(modulo)
    }

    private fun productMod(
        from: Int,
        to: Int,
        modulo: Int,
    ): Long = (from..to).fold(1L) { acc, i -> (acc * i) % modulo }

    // Returns (base^exponent) % modulo.
    private fun quickPow(
        base: Long,
        exponent: Int,
        modulo: Int,
    ): Long {
        var result = 1L
        var b = base
        var e = exponent
        while (e > 0) {
            if (e and 1 == 1) {
                result = (result * b) % modulo
            }
            b = (b * b) % modulo
            e = e shr 1
        }
        return result
    }
}
