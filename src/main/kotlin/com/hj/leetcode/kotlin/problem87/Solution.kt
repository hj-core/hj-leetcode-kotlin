package com.hj.leetcode.kotlin.problem87

/**
 * LeetCode page: [87. Scramble String](https://leetcode.com/problems/scramble-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N^4) and Space O(N^3) where N is the length of s1 and s2;
     */
    fun isScramble(s1: String, s2: String): Boolean {
        val inputLength = s1.length
        // subResults[i][j][k] ::= is s2[j until j+k] a scrambled string of s1[i until i+k]
        val subResults = subResultsHolder(inputLength)
        updateBaseCases(s1, s2, subResults)
        updateRestCases(inputLength, subResults)
        return originalProblem(subResults)
    }

    private fun subResultsHolder(inputLength: Int): Array<Array<BooleanArray>> {
        return Array(inputLength) { startS1 ->
            Array(inputLength) { startS2 ->
                val maxSubLength = inputLength - maxOf(startS1, startS2)
                BooleanArray(maxSubLength + 1)
            }
        }
    }

    private fun updateBaseCases(s1: String, s2: String, subResults: Array<Array<BooleanArray>>) {
        for (startS1 in s1.indices) {
            for (startS2 in s2.indices) {
                subResults[startS1][startS2][0] = true
                subResults[startS1][startS2][1] = s1[startS1] == s2[startS2]
            }
        }
    }

    private fun updateRestCases(inputLength: Int, subResults: Array<Array<BooleanArray>>) {
        for (subLength in 2..inputLength) {
            for (startS1 in 0..inputLength - subLength) {
                for (startS2 in 0..inputLength - subLength) {
                    updateSubResult(startS1, startS2, subLength, subResults)
                }
            }
        }
    }

    private fun updateSubResult(
        startS1: Int,
        startS2: Int,
        subLength: Int,
        subResults: Array<Array<BooleanArray>>
    ) {
        subResults[startS1][startS2][subLength] =
            (1 until subLength).any { splitLength ->
                isScrambleWithoutSwap(startS1, startS2, subLength, splitLength, subResults) ||
                        isScrambleWithSwap(startS1, startS2, subLength, splitLength, subResults)
            }
    }

    private fun isScrambleWithoutSwap(
        startS1: Int,
        startS2: Int,
        subLength: Int,
        splitLength: Int,
        subResults: Array<Array<BooleanArray>>
    ): Boolean {

        val isFirstScramble = subResults[startS1][startS2][splitLength]
        val isSecondScramble = subResults[startS1 + splitLength][startS2 + splitLength][subLength - splitLength]
        return isFirstScramble && isSecondScramble
    }

    private fun isScrambleWithSwap(
        startS1: Int,
        startS2: Int,
        subLength: Int,
        splitLength: Int,
        subResults: Array<Array<BooleanArray>>
    ): Boolean {

        val isFirstScramble = subResults[startS1][startS2 + subLength - splitLength][splitLength]
        val isSecondScramble = subResults[startS1 + splitLength][startS2][subLength - splitLength]
        return isFirstScramble && isSecondScramble
    }

    private fun originalProblem(subResults: Array<Array<BooleanArray>>): Boolean {
        return subResults[0][0].last()
    }
}