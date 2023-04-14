package com.hj.leetcode.kotlin.problem516

/**
 * LeetCode page: [516. Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the length of s;
     */
    fun longestPalindromeSubseq(s: String): Int {
        // subResults[i][j] ::= longestPalindromeSubseq(s.slice(i until i+j))
        val subResults = subResultHolder(s.length).apply {
            updateBaseCases(this)
            updateRemainingCases(s, this)
        }
        return originalProblem(subResults)
    }

    private fun subResultHolder(inputLength: Int): Array<IntArray> {
        return Array(inputLength) { start ->
            val maxSubStringLength = inputLength - start
            IntArray(maxSubStringLength + 1)
        }
    }

    private fun updateBaseCases(subResultHolder: Array<IntArray>) {
        for (start in subResultHolder.indices) {
            subResultHolder[start][0] = 0
            subResultHolder[start][1] = 1
        }
    }

    private fun updateRemainingCases(inputString: String, subResultHolder: Array<IntArray>) {
        val inputLength = subResultHolder.size
        for (length in 2..inputLength) {
            val maxSubStringStart = inputLength - length
            for (start in 0..maxSubStringStart) {
                val isHeadTailTheSame =  inputString[start] == inputString[start + length - 1]
                val subResult = if (isHeadTailTheSame) {
                    2 + subResultHolder[start + 1][length - 2]
                } else {
                    maxOf(subResultHolder[start][length - 1], subResultHolder[start + 1][length - 1])
                }
                subResultHolder[start][length] = subResult
            }
        }
    }

    private fun originalProblem(subResultHolder: Array<IntArray>): Int {
        return subResultHolder[0].last()
    }
}