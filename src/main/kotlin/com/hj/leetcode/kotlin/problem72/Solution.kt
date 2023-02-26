package com.hj.leetcode.kotlin.problem72

/**
 * LeetCode page: [72. Edit Distance](https://leetcode.com/problems/edit-distance/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the length of word1 and word2;
     */
    fun minDistance(word1: String, word2: String): Int {
        // holder[i][j] = minDistance(word1[i:], word2[j:])
        val holder = createSubResultHolder(word1, word2)

        return holder.run {
            setBaseCases(this, word1, word2)
            propagateRelation(this, word1, word2)
            solveOriginalProblem(this)
        }
    }

    private fun createSubResultHolder(word1: String, word2: String): Array<IntArray> {
        return Array(word1.length + 1) { IntArray(word2.length + 1) }
    }

    private fun setBaseCases(subResultHolder: Array<IntArray>, word1: String, word2: String) {
        val len1 = word1.length
        val len2 = word2.length

        for (i in 0..len1) {
            subResultHolder[i][len2] = len1 - i
        }

        for (j in 0..len2) {
            subResultHolder[len1][j] = len2 - j
        }
    }

    private fun propagateRelation(subResultHolder: Array<IntArray>, word1: String, word2: String) {
        for (i in word1.length - 1 downTo 0) {
            for (j in word2.length - 1 downTo 0) {
                solveAndStoreSubResult(subResultHolder, i, j, word1, word2)
            }
        }
    }

    private fun solveAndStoreSubResult(
        subResultHolder: Array<IntArray>,
        i: Int,
        j: Int,
        word1: String,
        word2: String
    ) {
        val subResult = if (word1[i] == word2[j]) {
            subResultHolder[i + 1][j + 1]
        } else {
            1 + minOf(
                subResultHolder[i][j + 1],
                subResultHolder[i + 1][j],
                subResultHolder[i + 1][j + 1]
            )
        }
        subResultHolder[i][j] = subResult
    }

    private fun solveOriginalProblem(subResultHolder: Array<IntArray>): Int {
        return subResultHolder[0][0]
    }
}