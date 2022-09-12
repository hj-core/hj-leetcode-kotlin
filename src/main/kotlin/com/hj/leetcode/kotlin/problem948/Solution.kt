package com.hj.leetcode.kotlin.problem948

/**
 * LeetCode page: [948. Bag of Tokens](https://leetcode.com/problems/bag-of-tokens/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of tokens;
     */
    fun bagOfTokensScore(tokens: IntArray, power: Int): Int {
        val sortedTokens = tokens.clone().apply { sort() }
        var maxScore = 0
        var currScore = 0
        var currPower = power
        var flippedMinMaxPairs = 0
        var nextTokenIndex = 0
        while (
            nextTokenIndex <= sortedTokens.lastIndex - flippedMinMaxPairs &&
            maxScore < tokens.size - (flippedMinMaxPairs shl 1)
        ) {
            currPower -= sortedTokens[nextTokenIndex]
            if (currPower >= 0) {
                currScore++
                maxScore = maxOf(maxScore, currScore)
            } else {
                if (currScore == 0) break
                currPower += sortedTokens[sortedTokens.lastIndex - flippedMinMaxPairs]
                flippedMinMaxPairs++
            }
            nextTokenIndex++
        }
        return maxScore
    }
}