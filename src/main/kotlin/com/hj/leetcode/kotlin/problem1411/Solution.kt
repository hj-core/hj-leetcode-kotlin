package com.hj.leetcode.kotlin.problem1411

/**
 * LeetCode page: [1411. Number of Ways to Paint N × 3 Grid](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/);
 */
class Solution {
    val module = 1_000_000_007

    // Complexity:
    // Time O(Log n) and Space O(1).
    fun numOfWays(n: Int): Int {
        if (n == 1) {
            return 12
        }

        val a =
            matPowMod(
                arrayOf(longArrayOf(3, 2), longArrayOf(2, 2)),
                n - 1,
            )

        return (
            (a[0][0] + a[0][1] + a[1][0] + a[1][1]) * 6 % module
        ).toInt()
    }

    // Returns a^exp % module where a is a 2 by 2 matrix.
    private fun matPowMod(
        a: Array<LongArray>,
        exp: Int,
    ): Array<LongArray> {
        var result =
            arrayOf(
                longArrayOf(1, 0),
                longArrayOf(0, 1),
            )

        var newBase = a
        var newExp = exp

        while (newExp > 0) {
            if (newExp and 1 == 1) {
                result = matMulMod(result, newBase)
            }

            newBase = matMulMod(newBase, newBase)
            newExp = newExp shr 1
        }
        return result
    }

    // Returns a * b % module where a and b are 2 by 2 matrices.
    fun matMulMod(
        a: Array<LongArray>,
        b: Array<LongArray>,
    ): Array<LongArray> =
        arrayOf(
            longArrayOf(
                (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % module,
                (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % module,
            ),
            longArrayOf(
                (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % module,
                (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % module,
            ),
        )
}
