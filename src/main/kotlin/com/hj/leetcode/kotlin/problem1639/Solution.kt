package com.hj.leetcode.kotlin.problem1639

/**
 * LeetCode page: [1639. Number of Ways to Form a Target String Given a Dictionary](https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/);
 */
class Solution {

    private val mod = 1_000_000_007

    /* Complexity:
     * Time O(MN+KN) and Space O(K) where M, N and K are the size of words, the length of each word,
     * and the length of target, respectively.
     */
    fun numWays(words: Array<String>, target: String): Int {
        val wordCount = WordCount(words)
        /* subResult[i]@j ::= number of ways to form suffix of target that starts from index i,
         * using the columns starting from index j;
         */
        val subResult = subResultHolder(target).apply {
            // Base case is j equals the length of word
            updateBaseCases(target, this)
            // Update the sub result in decreasing j, until j equals 0
            updateRemainingCases(words, target, wordCount, this)
        }
        return originalProblem(subResult)
    }

    private class WordCount(words: Array<String>) {

        private val count = count(words)

        private fun count(words: Array<String>): Array<IntArray> {
            val wordLength = words[0].length
            val count = Array(wordLength) { IntArray(26) }
            for (word in words) {
                for ((index, char) in word.withIndex()) {
                    count[index][char.intIndex()]++
                }
            }
            return count
        }

        private fun Char.intIndex(): Int = this - 'a'

        fun count(matching: Char, atIndex: Int): Int {
            return count[atIndex][matching.intIndex()]
        }
    }

    private fun subResultHolder(target: String): IntArray {
        return IntArray(target.length + 1)
    }

    private fun updateBaseCases(target: String, subResultHolder: IntArray) {
        // Using column at word.length, i.e. empty column, to form suffixes of target
        for (suffixStart in target.indices) {
            subResultHolder[suffixStart] = 0
        }
        subResultHolder[target.length] = 1
    }

    private fun updateRemainingCases(
        words: Array<String>,
        target: String,
        wordCount: WordCount,
        subResultHolder: IntArray
    ) {
        val wordLength = words[0].length
        for (startColumn in wordLength - 1 downTo 0) {
            for (startSuffix in target.indices) {
                val numWaysUsingStartColumn =
                    wordCount.count(target[startSuffix], startColumn).toLong() * subResultHolder[startSuffix + 1]
                val numWaysNotUsingStartColumn = subResultHolder[startSuffix]
                val subResult = (numWaysUsingStartColumn + numWaysNotUsingStartColumn) % mod
                subResultHolder[startSuffix] = subResult.toInt()
            }
        }
    }

    private fun originalProblem(subResultHolder: IntArray): Int {
        return subResultHolder[0]
    }
}