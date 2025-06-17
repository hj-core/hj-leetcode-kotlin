package com.hj.leetcode.kotlin.problem3405

/**
 * LeetCode page: [3405. Count the Number of Arrays with K Matching Adjacent Elements](https://leetcode.com/problems/count-the-number-of-arrays-with-k-matching-adjacent-elements/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(n).
    fun countGoodArrays(
        n: Int,
        m: Int,
        k: Int,
    ): Int {
        if (m == 1) {
            return if (k == n - 1) 1 else 0
        }

        val modulo = 1_000_000_007
        val calc = CombModCalculator(n - 1, modulo)

        // We consider the n-1 shared edges between adjacent elements. There are
        // n-1-k edges that connect different adjacent elements. We then partition
        // the array at these edges, resulting in n-k partitions, each containing
        // the same number, and adjacent partitions contain different numbers.

        var result = calc.combMod(n - 1, n - 1 - k).toLong()
        result = result * m % modulo
        result = result * powerMod(m - 1, n - k - 1, modulo) % modulo
        return result.toInt()
    }
}

private class CombModCalculator(
    maxN: Int,
    val primeModulo: Int,
) {
    // facMod[i] ::= i! % primeModulo
    private val facMod = IntArray(maxN + 1)

    init {
        facMod[0] = 1
        for (i in 1..<facMod.size) {
            facMod[i] = (i.toLong() * facMod[i - 1] % primeModulo).toInt()
        }
    }

    // invFacMod[i] ::= the modular inverse of i! % primeModulo
    private val invFacMod = IntArray(maxN + 1)

    init {
        invFacMod[maxN] = powerMod(facMod[maxN], primeModulo - 2, primeModulo)
        for (i in maxN downTo 1) {
            invFacMod[i - 1] = ((i.toLong() * invFacMod[i]) % primeModulo).toInt()
        }
    }

    // Computes C(n, k) % primeModulo.
    fun combMod(
        n: Int,
        k: Int,
    ): Int {
        if (n < k) {
            return 0
        }
        var result = facMod[n].toLong()
        result = (result * invFacMod[k]) % primeModulo
        result = (result * invFacMod[n - k]) % primeModulo
        return result.toInt()
    }
}

// Computes (base^exp) % modulo.
private fun powerMod(
    base: Int,
    exp: Int,
    modulo: Int,
): Int {
    var result = 1L
    var newBase = (base % modulo).toLong()
    var newExp = exp

    while (newExp > 0) {
        if (newExp and 1 == 1) {
            result = (result * newBase) % modulo
        }
        newBase = (newBase * newBase) % modulo
        newExp = newExp shr 1
    }
    return result.toInt()
}
