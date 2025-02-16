package com.hj.leetcode.kotlin.problem1718

/**
 * LeetCode page: [1718. Construct the Lexicographically Largest Valid Sequence](https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/);
 */
class Solution {
    // Complexity:
    // Time O(n!*n) and Space O(n).
    fun constructDistancedSequence(n: Int): IntArray {
        require(n <= 20) { "n doesn't meet the constraints" }
        val result = IntArray(n * 2 - 1)
        dfs(n, 0, 0, result)
        return result
    }

    // dfs assigns each index in the result, starting from 0, a number according to the rule
    // and as large as possible.
    // Returns true on the first successful attempt.
    private fun dfs(
        n: Int,
        index: Int,
        usedBitMap: Int,
        result: IntArray,
    ): Boolean {
        if (index == result.size) {
            return true
        }
        if (result[index] > 0) {
            return dfs(n, index + 1, usedBitMap, result)
        }

        for (num in n downTo 1) {
            if (usedBitMap and (1 shl num) != 0) {
                continue
            }
            if (num > 1 && (result.size <= index + num || result[index + num] > 0)) {
                continue
            }

            result[index] = num
            if (num > 1) {
                result[index + num] = num
            }

            if (dfs(n, index + 1, usedBitMap xor (1 shl num), result)) {
                return true
            }
            result[index] = 0
            if (num > 1) {
                result[index + num] = 0
            }
        }
        return false
    }
}
