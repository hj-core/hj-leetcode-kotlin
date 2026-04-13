package com.hj.leetcode.kotlin.problem1320

import kotlin.math.abs

/**
 * LeetCode page: [1320. Minimum Distance to Type a Word Using Two Fingers](https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/);
 */
class Solution {
    // moveDistance[i][j]:= the distance moving from 'A'+i to 'A'+j
    private val moveDistance = Array(26) { IntArray(26) }

    init {
        initMoveDistance()
    }

    private fun initMoveDistance() {
        val columns = 6
        for (i in 0..<26) {
            val r0 = i / columns
            val c0 = i % columns

            for (j in i + 1..<26) {
                val r1 = j / columns
                val c1 = j % columns
                moveDistance[i][j] = abs(r0 - r1) + abs(c0 - c1)
                moveDistance[j][i] = moveDistance[i][j]
            }
        }
    }

    // Complexity:
    // Time O(MN) and Space O(M) where N is the length of word and M is the
    // size of char set (i.e., 26). This does not account the one-time overhead
    // of `moveDistance` which has a cost of Time O(M^2) and Space O(M^2).
    fun minimumDistance(word: String): Int {
        // dp[index&1][i]:= the minimum distance to type word[..=index] with
        // fingers ending at 'A'+i and word[index].
        //
        // Note that one of the fingers must place at word[index].
        val dp = Array(2) { IntArray(26) }

        for (index in 1..<word.length) {
            val currDp = dp[1 and index]
            val prevDp = dp[1 and index xor 1]

            val k = word[index] - 'A'
            val j = word[index - 1] - 'A'

            // Move from (i, j) to (i, k)
            for (i in 0..<26) {
                currDp[i] = prevDp[i] + moveDistance[j][k]
            }
            // Move from (i, j) to (k, j)
            for (i in 0..<26) {
                currDp[j] = minOf(currDp[j], prevDp[i] + moveDistance[i][k])
            }
        }

        return dp[1 and word.length xor 1].min()
    }

    // Complexity:
    // Time O(MN) and Space O(M) where N is the length of word and M is the
    // size of char set (i.e., 26). This does not account the one-time overhead
    // of `moveDistance` which has a cost of Time O(M^2) and Space O(M^2).
    fun minimumDistance2(word: String): Int {
        // dp[i]:= the minimum distance to type word[..=index] with
        // fingers ending at 'A'+i and word[index] minus the minimum distance
        // if only one finger is used.
        //
        // Note that one of the fingers must place at word[index].
        val dp = IntArray(26)
        var oneFingerCost = 0

        for (index in 1..<word.length) {
            val j = word[index - 1] - 'A'
            val k = word[index] - 'A'
            oneFingerCost += moveDistance[j][k]

            for (i in 0..<26) {
                dp[j] = minOf(dp[j], dp[i] + moveDistance[i][k] - moveDistance[j][k])
            }
        }

        return dp.min() + oneFingerCost
    }
}
