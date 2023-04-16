package com.hj.leetcode.kotlin.problem1639

/**
 * LeetCode page: [1639. Number of Ways to Form a Target String Given a Dictionary](https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/);
 */
class Solution {

    private val mod = 1_000_000_007

    /* Complexity:
     * Time O(MN+KN) and Space O(MN) where M, N and K are the size of words, length of each word,
     * and length of target, respectively.
     */
    fun numWays(words: Array<String>, target: String): Int {
        // charCounts[i][j] ::= the number of words that its jth char is the same as target's ith char;
        val charCounts = charCountAtEachColumn(words)
        // subResults[i][j] ::= the number of ways using word from column i to form suffix of target from index j
        val subResults = subResultHolder(words, target).apply {
            updateBaseCases(target, this)
            updateRemainingCases(target, charCounts, this)
        }
        return originalProblem(subResults)
    }

    private fun charCountAtEachColumn(words: Array<String>): Array<IntArray> {
        val numColumns = words[0].length
        val count = Array(numColumns) { IntArray(26) }
        for (word in words) {
            for ((column, char) in word.withIndex()) {
                count[column][char.intIndex()]++
            }
        }
        return count
    }

    private fun Char.intIndex(): Int = this - 'a'

    private fun subResultHolder(words: Array<String>, target: String): Array<IntArray> {
        val numColumns = words[0].length
        return Array(numColumns + 1) { IntArray(target.length) }
    }

    private fun updateBaseCases(target: String, subResultHolder: Array<IntArray>) {
        for (start in target.indices.reversed()) {
            subResultHolder.last()[start] = 0
        }
    }

    private fun updateRemainingCases(
        target: String,
        charCounts: Array<IntArray>,
        subResultHolder: Array<IntArray>
    ) {
        for (column in subResultHolder.lastIndex - 1 downTo 0) {
            for (start in target.indices.reversed()) {
                val charCount = charCounts[column][target[start].intIndex()]
                val numWaysUsingColumn = charCount.toLong() * subResultHolder[column + 1].getOrElse(start + 1) { 1 }
                val numWaysNotUsingColumn = subResultHolder[column + 1][start]
                val subResult = ((numWaysUsingColumn + numWaysNotUsingColumn) % mod).toInt()
                subResultHolder[column][start] = subResult
            }
        }
    }

    private fun originalProblem(subResultHolder: Array<IntArray>): Int {
        return subResultHolder[0][0]
    }
}