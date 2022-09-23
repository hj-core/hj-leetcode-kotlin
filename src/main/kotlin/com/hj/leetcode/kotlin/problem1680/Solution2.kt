package com.hj.leetcode.kotlin.problem1680

/**
 * LeetCode page: [1680. Concatenation of Consecutive Binary Numbers](https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/);
 *
 * Note: There is a solution posted by leetCode staff and claims to have time O((LogN)^2), see [HERE](https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/discuss/963886).
 * I cannot understand...
 */
class Solution2 {
    /* Complexity:
     * Time O((LogN)^2) and Space O(1) where N equals n;
     */
    fun concatenatedBinary(n: Int): Int {
        /* The idea is to represent the concatenation through a series of matrix operations, which contains
         * multiplication of matrix powers. The improvement comes from algorithm that takes Log rather than
         * linear order complexity to calculate the power, which is similar to 2^16 = (2^2)^8 = (4^2)^4.
         *
         * Recursive relation via matrix operation:
         *          | ans_n |           | 2^k  1   0  |           | ans_(n-1) |
         *    mat ( | n + 1 | ) = mat ( |  0   1   1  | ) * mat ( |     n     | ) where k is the bitLength of n;
         *          |   1   |           |  0   0   1  |           |     1     |
         */
        var vector = listOf(longArrayOf(1L), longArrayOf(2L), longArrayOf(1L))
        var prevBitValue = 2L
        val mod = 1_000_000_007

        while (prevBitValue <= n) {
            val bitValue = prevBitValue shl 1
            val base = getBaseMultiplier(bitValue)
            val pow = if (n < bitValue) n - prevBitValue + 1 else prevBitValue
            val multiplier = powerAndRem(base, pow, mod)
            vector = multiplyAndRem(vector, multiplier, mod)
            prevBitValue = bitValue
        }
        return vector[0][0].toInt()
    }

    private fun getBaseMultiplier(bitValue: Long): List<LongArray> {
        return listOf(
            longArrayOf(bitValue, 1L, 0),
            longArrayOf(0, 1L, 1L),
            longArrayOf(0, 0, 1L)
        )
    }

    private fun powerAndRem(mat: List<LongArray>, pow: Long, mod: Int): List<LongArray> {
        require(pow > 0)
        val result = if (pow == 1L) {
            rem(mat, mod)
        } else {
            val square = multiplyAndRem(mat, mat, mod)
            val floorPow = powerAndRem(square, pow shr 1, mod)
            val isEven = pow and 1L == 0L
            if (isEven) floorPow else multiplyAndRem(mat, floorPow, mod)
        }
        return result
    }

    private fun rem(mat: List<LongArray>, mod: Int): List<LongArray> {
        return List(mat.size) { row ->
            LongArray(mat[row].size) { column ->
                mat[row][column] % mod
            }
        }
    }

    private fun multiplyAndRem(mat: List<LongArray>, multiplier: List<LongArray>, mod: Int): List<LongArray> {
        return List(multiplier.size) { row ->
            LongArray(mat[row].size) { column ->
                var value = 0L
                for (index in multiplier[row].indices) {
                    value += multiplier[row][index] * mat[index][column]
                }
                value % mod
            }
        }
    }
}