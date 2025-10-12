package com.hj.leetcode.kotlin.problem3539

/**
 * LeetCode page: [3539. Find Sum of Array Product of Magical Sequences](https://leetcode.com/problems/find-sum-of-array-product-of-magical-sequences/);
 */
class Solution {
    private val mod = 1_000_000_007

    // Complexity:
    // Time O(n*k*m^3) and Space O(k*m^2).
    fun magicalSum(
        m: Int,
        k: Int,
        nums: IntArray,
    ): Int {
        val facModM = facMod(m) // m! % mod

        val invFacMod = LongArray(m + 1) // invFacMod[n] * n! % mod = 1
        invFacMod[m] = expMod(facModM, mod - 2)
        for (i in m - 1 downTo 0) {
            invFacMod[i] = invFacMod[i + 1] * (i + 1) % mod
        }

        // dp[i][j][carry][cnt]*facMod[j]:=
        // The sum of product sums from sequences that consist of
        // j repeatable indices from 0..<i, and whose binary sum S
        // has cnt bits set before 2^i, and S >> i equals carry.
        //
        // We only keep the two most recent sub results.
        var dp0 = Array(m + 1) { Array(m / 2 + 1) { LongArray(k + 1) } }
        var dp1 = Array(m + 1) { Array(m / 2 + 1) { LongArray(k + 1) } }
        dp1[0][0][0] = 1 // i=0

        val numPow = LongArray(m + 1) // numPow[exp]:= nums[i-1]^exp % mod

        for (i in 1..nums.size) {
            // Make room for the new i
            dp0 = dp1.also { dp1 = dp0 }
            for (j in dp1.indices) {
                for (carry in dp1[j].indices) {
                    for (cnt in dp1[j][carry].indices) {
                        dp1[j][carry][cnt] = 0
                    }
                }
            }

            numPow[0] = 1
            for (exp in 1..m) {
                numPow[exp] = numPow[exp - 1] * nums[i - 1] % mod
            }

            for (oldJ in dp0.indices) {
                for (oldCarray in dp0[oldJ].indices) {
                    for (oldCnt in dp0[oldJ][oldCarray].indices) {
                        if (dp0[oldJ][oldCarray][oldCnt] == 0L) {
                            continue
                        }

                        for (pick in 0..<m - oldJ + 1) {
                            val newCnt = oldCnt + ((oldCarray + pick) and 1)
                            if (newCnt > k) {
                                continue
                            }

                            val newJ = oldJ + pick
                            val newCarry = (oldCarray + pick) shr 1

                            dp1[newJ][newCarry][newCnt] += (
                                dp0[oldJ][oldCarray][oldCnt] *
                                    numPow[pick] % mod *
                                    invFacMod[pick] % mod
                            )

                            if (dp1[newJ][newCarry][newCnt] >= mod) {
                                dp1[newJ][newCarry][newCnt] -= mod
                            }
                        }
                    }
                }
            }
        }

        var result = 0L
        for (carry in dp1[m].indices) {
            val cnt = k - carry.countOneBits()
            if (cnt >= 0) {
                result += dp1[m][carry][cnt]
            }
        }
        result = result % mod * facModM % mod
        return result.toInt()
    }

    // Computes n! % mod.
    private fun facMod(n: Int): Long = (2..n).fold(1L) { acc, num -> acc * num % mod }

    // Computes base^exp % mod.
    private fun expMod(
        base: Long,
        exp: Int,
    ): Long {
        var result = 1L
        var newBase = base % mod
        var newExp = exp
        while (newExp > 0) {
            if (newExp and 1 == 1) {
                result = result * newBase % mod
            }
            newBase = newBase * newBase % mod
            newExp = newExp shr 1
        }
        return result
    }
}
