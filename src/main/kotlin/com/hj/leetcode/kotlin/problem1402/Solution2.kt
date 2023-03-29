package com.hj.leetcode.kotlin.problem1402

/**
 * LeetCode page: [1402. Reducing Dishes](https://leetcode.com/problems/reducing-dishes/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of satisfaction;
     */
    fun maxSatisfaction(satisfaction: IntArray): Int {
        val sortedSatisfaction = satisfaction.sorted()
        val maxDishes = satisfaction.size
        var end = -1
        /* prefixResult[j] ::= when j dishes are prepared, the result of current sortedSatisfaction
         * prefix array
         */
        val prefixResult = IntArray(maxDishes + 1)
        var maxResult = 0
        repeat(sortedSatisfaction.size) {
            updateNextPrefixResult(end, sortedSatisfaction, prefixResult)
            end++
            maxResult = maxOf(maxResult, prefixResult.max()!!)
        }
        return maxResult
    }

    private fun updateNextPrefixResult(
        currentEnd: Int,
        sortedSatisfaction: List<Int>,
        currentResult: IntArray
    ) {
        val newEnd = currentEnd + 1
        val maxDishes = newEnd + 1
        val likeOfNewDish = sortedSatisfaction[newEnd]
        // update for the case that all dishes are prepared
        currentResult[maxDishes] = currentResult[maxDishes - 1] + maxDishes * likeOfNewDish
        // update for the remaining cases
        for (numDishes in maxDishes - 1 downTo 1) {
            val resultIfDiscardNewDish = currentResult[numDishes]
            val resultIfPrepareNewDish =
                currentResult[numDishes - 1] + numDishes * likeOfNewDish
            currentResult[numDishes] = maxOf(resultIfDiscardNewDish, resultIfPrepareNewDish)
        }
    }
}