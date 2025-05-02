package com.hj.leetcode.kotlin.problem838

/**
 * LeetCode page: [838. Push Dominoes](https://leetcode.com/problems/push-dominoes/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of dominoes.
    fun pushDominoes(dominoes: String): String {
        val builder = StringBuilder(dominoes.length)
        var prevForceIndex = 0
        var nextForceIndex = 0

        for ((i, domino) in dominoes.withIndex()) {
            if (domino != '.') {
                prevForceIndex = i
                builder.append(domino)
            } else {
                nextForceIndex = maxOf(nextForceIndex, i)
                while (nextForceIndex < dominoes.length && dominoes[nextForceIndex] == '.') {
                    nextForceIndex++
                }
                val direction = computeDirection(dominoes, i, prevForceIndex, nextForceIndex)
                builder.append(direction)
            }
        }
        return builder.toString()
    }

    private fun computeDirection(
        dominoes: String,
        index: Int,
        prevForceIndex: Int,
        nextForceIndex: Int,
    ): Char {
        var distantPushR = dominoes.length
        if (dominoes[prevForceIndex] == 'R') {
            distantPushR = index - prevForceIndex
        }

        var distantPushL = dominoes.length
        if (nextForceIndex < dominoes.length && dominoes[nextForceIndex] == 'L') {
            distantPushL = nextForceIndex - index
        }

        return when {
            distantPushR < distantPushL -> 'R'
            distantPushR > distantPushL -> 'L'
            else -> '.'
        }
    }
}
