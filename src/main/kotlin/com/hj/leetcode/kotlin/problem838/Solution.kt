package com.hj.leetcode.kotlin.problem838

/**
 * LeetCode page: [838. Push Dominoes](https://leetcode.com/problems/push-dominoes/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of dominoes;
     */
    fun pushDominoes(dominoes: String): String {
        val ans = StringBuilder(dominoes)
        var leftBound = 0

        loop@ for (rightBound in 1..dominoes.lastIndex) {
            val fallDirection = getFallDirection(dominoes, leftBound, rightBound)
            val range = leftBound..rightBound

            when (fallDirection) {
                FallDirection.Left -> fallToLeft(ans, range)
                FallDirection.Right -> fallToRight(ans, range)
                FallDirection.Inward -> fallInward(ans, range)
                FallDirection.Unchanged -> {}
                FallDirection.Unknown -> continue@loop
            }
            leftBound = rightBound
        }
        return ans.toString()
    }

    private fun getFallDirection(dominoes: String, leftBound: Int, rightBound: Int): FallDirection {
        return when (dominoes[leftBound]) {
            '.' -> when (dominoes[rightBound]) {
                '.' -> FallDirection.Unknown
                'L' -> FallDirection.Left
                'R' -> FallDirection.Unchanged
                else -> throw IllegalStateException()
            }
            'L' -> FallDirection.Unchanged
            'R' -> when (dominoes[rightBound]) {
                '.' -> if (rightBound == dominoes.lastIndex) FallDirection.Right else FallDirection.Unknown
                'L' -> FallDirection.Inward
                'R' -> FallDirection.Right
                else -> throw IllegalStateException()
            }
            else -> throw IllegalStateException()
        }
    }

    enum class FallDirection { Left, Right, Inward, Unchanged, Unknown }

    private fun fallToLeft(dominoes: StringBuilder, range: IntRange) {
        fall(dominoes, range, 'L')
    }

    private fun fall(dominoes: StringBuilder, range: IntRange, direction: Char) {
        for (index in range) {
            dominoes[index] = direction
        }
    }

    private fun fallToRight(dominoes: StringBuilder, range: IntRange) {
        fall(dominoes, range, 'R')
    }

    private fun fallInward(dominoes: StringBuilder, range: IntRange) {
        val width = range.last - range.first + 1
        val halfWidth = width shr 1

        val rangeToRight = range.first until range.first + halfWidth
        fallToRight(dominoes, rangeToRight)

        val rangeToLeft = range.last - halfWidth + 1..range.last
        fallToLeft(dominoes, rangeToLeft)
    }
}