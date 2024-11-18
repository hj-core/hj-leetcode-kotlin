package com.hj.leetcode.kotlin.problem1652

/**
 * LeetCode page: [1652. Defuse the Bomb](https://leetcode.com/problems/defuse-the-bomb/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the length of code.
     */
    fun decrypt(
        code: IntArray,
        k: Int,
    ): IntArray =
        when {
            k < 0 -> decryptNegativeK(code, k)
            k > 0 -> decryptPositiveK(code, k)
            else -> IntArray(code.size)
        }

    private fun decryptNegativeK(
        code: IntArray,
        k: Int,
    ): IntArray {
        val result = IntArray(code.size)
        result[0] = (code.size + k..<code.size).sumOf { code[it] }
        for (i in 1..<result.size) {
            result[i] = result[i - 1] + code[i - 1] - code[(i - 1 + k).mod(code.size)]
        }
        return result
    }

    private fun decryptPositiveK(
        code: IntArray,
        k: Int,
    ): IntArray {
        val result = IntArray(code.size)
        result[0] = (1..k).sumOf { code[it] }
        for (i in 1..<result.size) {
            result[i] = result[i - 1] + code[(i + k) % code.size] - code[i]
        }
        return result
    }
}
