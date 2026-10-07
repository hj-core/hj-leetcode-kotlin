package com.hj.leetcode.kotlin.problem301

/**
 * LeetCode page: [301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/);
 */
class Solution {
    // Complexity:
    // Time O(???) and Space O(???).
    fun removeInvalidParentheses(s: String): List<String> {
        val s = s.toCharArray()

        // determine the final number of parentheses and a mask for the lowercases
        var targetPair = 0
        var netOpen = 0
        var lastClose = -1
        var charMask = 0
        for ((i, c) in s.withIndex()) {
            when (c) {
                '(' -> {
                    netOpen++
                }

                ')' -> {
                    lastClose = i
                    if (netOpen > 0) {
                        targetPair++
                        netOpen--
                    }
                }

                else -> {
                    charMask = charMask or (1 shl i)
                }
            }
        }

        // there is only one way to process the substring after last ')'
        val reducedSize = lastClose + 1
        if (reducedSize == 0) {
            return listOf(convertMaskToString(s, charMask))
        }

        // dfs with memoization to determine the valid masks
        val visited =
            Array(reducedSize) {
                Array(targetPair + 1) {
                    BooleanArray(it + 1)
                }
            }
        val memoization =
            Array(reducedSize) {
                Array(targetPair + 1) {
                    Array(it + 1) { mutableListOf<Int>() }
                }
            }
        dfs(s, 0, 0, 0, targetPair, charMask, visited, memoization)
        return memoization[0][0][0].map { convertMaskToString(s, it) }
    }

    private fun dfs(
        s: CharArray,
        i: Int,
        prevOpen: Int,
        prevClose: Int,
        targetPair: Int,
        charMask: Int,
        visited: Array<Array<BooleanArray>>,
        memoization: Array<Array<Array<MutableList<Int>>>>,
    ): List<Int> {
        val reducedSize = memoization.size
        if (i == reducedSize) {
            if (prevOpen == targetPair && prevClose == targetPair) {
                return listOf(charMask ushr reducedSize shl reducedSize)
            }
            return emptyList()
        }

        val memo = memoization[i][prevOpen][prevClose]
        if (visited[i][prevOpen][prevClose]) {
            return memo
        }
        visited[i][prevOpen][prevClose] = true

        if (prevOpen == targetPair && prevClose == targetPair) {
            memo.add(charMask ushr i shl i)
            return memo
        }

        if (s[i] in 'a'..'z') {
            // identify the current group of lowercases
            var j = i
            while (j < reducedSize && s[j] in 'a'..'z') {
                j++
            }
            // pick all lowercases
            val mask = ((1 shl j) - 1) ushr i shl i
            for (m2 in dfs(s, j, prevOpen, prevClose, targetPair, charMask, visited, memoization)) {
                memo.add(mask or m2)
            }
            return memo
        }

        if (s[i] == '(') {
            // identify the current group of '('
            var j = i
            while (j < reducedSize && s[j] == '(') {
                j++
            }
            // skip the current group of '('
            memo.addAll(dfs(s, j, prevOpen, prevClose, targetPair, charMask, visited, memoization))
            // pick a prefix from the current group of '('
            var mask = 0
            val maxPick = minOf(j - i, targetPair - prevOpen)
            for (pick in 1..maxPick) {
                mask = 1 shl (i + pick - 1) or mask
                for (m2 in dfs(
                    s,
                    j,
                    prevOpen + pick,
                    prevClose,
                    targetPair,
                    charMask,
                    visited,
                    memoization,
                )) {
                    memo.add(mask or m2)
                }
            }
            return memo
        }

        if (s[i] == ')') {
            // identify the current group of ')'
            var j = i
            while (j < reducedSize && s[j] == ')') {
                j++
            }
            // skip the current group of ')'
            memo.addAll(dfs(s, j, prevOpen, prevClose, targetPair, charMask, visited, memoization))
            // pick a prefix from the current group of ')'
            var mask = 0
            val maxPick = minOf(j - i, targetPair - prevClose, prevOpen - prevClose)
            for (pick in 1..maxPick) {
                mask = 1 shl (i + pick - 1) or mask
                for (m2 in dfs(
                    s,
                    j,
                    prevOpen,
                    prevClose + pick,
                    targetPair,
                    charMask,
                    visited,
                    memoization,
                )) {
                    memo.add(mask or m2)
                }
            }
            return memo
        }

        throw IllegalStateException("Should have early returned")
    }

    private fun convertMaskToString(
        s: CharArray,
        mask: Int,
    ): String =
        buildString(mask.countOneBits()) {
            for (i in s.indices) {
                if (mask ushr i and 1 == 1) {
                    append(s[i])
                }
            }
        }
}
