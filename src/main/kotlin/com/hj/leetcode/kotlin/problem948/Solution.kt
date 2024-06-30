package com.hj.leetcode.kotlin.problem948

/**
 * LeetCode page: [948. Bag of Tokens](https://leetcode.com/problems/bag-of-tokens/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of tokens;
     */
    fun bagOfTokensScore(tokens: IntArray, power: Int): Int {
        if (power == 0 || tokens.isEmpty()) {
            return 0
        }

        val sortedTokens = tokens.sorted()
        if (power < sortedTokens[0]) {
            return 0
        }

        var result = 0
        var remainingPower = power
        var left = 0
        var right = sortedTokens.lastIndex

        while (left < right) {
            if (sortedTokens[left] <= remainingPower) {
                remainingPower -= sortedTokens[left]
                result++
                left++
            } else {
                remainingPower += sortedTokens[right]
                result--
                right--
            }
        }
        if (sortedTokens[left] <= remainingPower) {
            result++
        }
        return result
    }
}