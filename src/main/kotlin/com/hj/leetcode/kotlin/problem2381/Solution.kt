package com.hj.leetcode.kotlin.problem2381

/**
 * LeetCode page: [2381. Shifting Letters II](https://leetcode.com/problems/shifting-letters-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(M)
     * where M and N are the length of s and shifts, respectively.
     */
    fun shiftingLetters(
        s: String,
        shifts: Array<IntArray>,
    ): String {
        val rotations = netRotationsEachPosition(s, shifts)
        return rotated(s, rotations)
    }

    private fun netRotationsEachPosition(
        s: String,
        shifts: Array<IntArray>,
    ): IntArray {
        val result = IntArray(s.length + 1)
        for ((start, end, direction) in shifts) {
            val rotation = if (direction == 1) 1 else -1
            result[start] += rotation
            result[end + 1] -= rotation
        }
        for (i in 1..s.length) {
            result[i] += result[i - 1]
        }
        return result
    }

    private fun rotated(
        s: String,
        rotations: IntArray,
    ): String {
        val result = StringBuilder(s.length)
        for (i in s.indices) {
            val c = 'a' + (s[i] - 'a' + rotations[i]).mod(26)
            result.append(c)
        }
        return result.toString()
    }
}
