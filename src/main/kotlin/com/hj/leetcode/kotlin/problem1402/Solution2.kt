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
        /* prefixMaxResult[d] ::= the max result of the current sortedSatisfaction prefix
         * array when total d dishes are prepared
         */
        var end = -1
        val prefixMaxResult = IntArray(satisfaction.size + 1)
        var maxResult = 0
        repeat(sortedSatisfaction.size) {
            updateToNextPrefix(end, sortedSatisfaction, prefixMaxResult)
            end++
            maxResult = maxOf(maxResult, prefixMaxResult.max()!!)
        }
        return maxResult
    }

    private fun updateToNextPrefix(
        currentEnd: Int,
        sortedSatisfaction: List<Int>,
        prefixMaxResult: IntArray
    ) {
        val nextEnd = currentEnd + 1
        val maxDishes = nextEnd + 1
        val likeOfNewDish = sortedSatisfaction[nextEnd]
        // update for the case that all dishes are prepared
        prefixMaxResult[maxDishes] = prefixMaxResult[maxDishes - 1] + maxDishes * likeOfNewDish
        // update for cases that some dishes are prepared but not all
        for (numDishes in maxDishes - 1 downTo 1) {
            prefixMaxResult[numDishes] = maxOf(
                // case that the new dish is not prepared
                prefixMaxResult[numDishes],
                // case that the new dish is prepared
                prefixMaxResult[numDishes - 1] + numDishes * likeOfNewDish
            )
        }
    }
}