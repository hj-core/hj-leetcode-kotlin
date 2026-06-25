package com.hj.leetcode.kotlin.problem3700

/**
 * LeetCode page: [3700. Number of ZigZag Arrays II](https://leetcode.com/problems/number-of-zigzag-arrays-ii/);
 */
class Solution {
    // Complexity:
    // Time O(m^3 Log n) and Space O(m^2) where m is r - l.
    //
    // TODO: [Berlekamp–Massey algorithm](https://en.wikipedia.org/wiki/Berlekamp%E2%80%93Massey_algorithm)
    fun zigZagArrays(
        n: Int,
        l: Int,
        r: Int,
    ): Int {
        // Normalize the range [l, r] to [0, r-l]
        val r = r - l
        val modulus = 1_000_000_007

        val matA = Array(r + 1) { LongArray(r + 1) }
        for (i in 0..<r) {
            for (j in i + 1..r) {
                matA[i][j] = 1
            }
        }

        val matB = Array(r + 1) { LongArray(r + 1) }
        for (i in 1..r) {
            for (j in 0..<i) {
                matB[i][j] = 1
            }
        }

        val matC = matProductMod(matB, matA, modulus)

        var matD = matExpMod(matC, (n - 2) / 2, modulus)
        if (n and 1 == 1) {
            matD = matProductMod(matA, matD, modulus)
        }

        var vecX = Array(r + 1) { r -> longArrayOf(r.toLong()) }
        vecX = matProductMod(matD, vecX, modulus)

        val halfCount = vecX.fold(0L) { acc, v -> (acc + v[0]) % modulus }
        return (halfCount * 2).mod(modulus)
    }

    private fun matProductMod(
        left: Array<LongArray>,
        right: Array<LongArray>,
        modulus: Int,
    ): Array<LongArray> {
        val m = left.size
        val n = right.size
        val r = right[0].size

        val product = Array(m) { LongArray(r) }
        for (i in 0..<m) {
            for (j in 0..<r) {
                var value = 0L
                for (k in 0..<n) {
                    value = (value + left[i][k] * right[k][j]) % modulus
                }

                product[i][j] = value
            }
        }

        return product
    }

    private fun matExpMod(
        mat: Array<LongArray>,
        exponent: Int,
        modulus: Int,
    ): Array<LongArray> {
        var base = mat
        var exponent = exponent

        var result = identityMat(mat.size)
        while (exponent > 0) {
            if (exponent and 1 == 1) {
                result = matProductMod(base, result, modulus)
            }
            base = matProductMod(base, base, modulus)
            exponent = exponent shr 1
        }

        return result
    }

    private fun identityMat(size: Int): Array<LongArray> =
        Array(size) { r ->
            LongArray(size).apply { this[r] = 1L }
        }
}
