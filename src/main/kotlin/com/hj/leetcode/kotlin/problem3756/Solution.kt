package com.hj.leetcode.kotlin.problem3756

/**
 * LeetCode page: [3756. Concatenate Non-Zero Digits and Multiply by Sum II](https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Auxiliary Space O(N) where N is the length of s and
    // M is the length of queries.
    fun sumAndMultiply(
        s: String,
        queries: Array<IntArray>,
    ): IntArray {
        val modulus = 1_000_000_007L
        val modInv10 =
            run {
                var inv10 = 1L
                var base = 10L
                var exp = modulus - 2
                while (exp > 0) {
                    if (exp and 1L == 1L) {
                        inv10 = (inv10 * base) % modulus
                    }
                    exp = exp shr 1
                    base = (base * base) % modulus
                }
                inv10
            }

        val suffixSum = IntArray(s.length + 1)
        val suffixX = LongArray(s.length + 1)
        val suffixInv = LongArray(s.length + 1)
        suffixInv[s.length] = 1L
        var digitWeight = 1L

        for (i in s.indices.reversed()) {
            suffixSum[i] = suffixSum[i + 1]
            suffixX[i] = suffixX[i + 1]
            suffixInv[i] = suffixInv[i + 1]
            val digit = s[i] - '0'
            if (digit != 0) {
                suffixSum[i] += digit
                suffixX[i] = (suffixX[i] + digitWeight * digit) % modulus
                suffixInv[i] = (suffixInv[i] * modInv10) % modulus
                digitWeight = (digitWeight * 10) % modulus
            }
        }

        return IntArray(queries.size) {
            val (l, r) = queries[it]
            val sum = (suffixSum[l] - suffixSum[r + 1]).mod(modulus)

            val diff = (suffixX[l] - suffixX[r + 1]).mod(modulus)
            val x = (diff * suffixInv[r + 1]).mod(modulus)

            (x * sum).mod(modulus).toInt()
        }
    }
}
