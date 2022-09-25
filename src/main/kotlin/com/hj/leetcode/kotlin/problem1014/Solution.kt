package com.hj.leetcode.kotlin.problem1014

/**
 * LeetCode page: [1014. Best Sightseeing Pair](https://leetcode.com/problems/best-sightseeing-pair/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of values;
     */
    fun maxScoreSightseeingPair(values: IntArray): Int {
        var maxScore = getScore(values, 0, 1)
        var prevBestSpot = 0

        for (spot in 1..values.lastIndex) {
            val currMaxScoreBySpot = getScore(values, prevBestSpot, spot)
            maxScore = maxOf(maxScore, currMaxScoreBySpot)

            val isNewBestSpot = values[spot] > values[prevBestSpot] - (spot - prevBestSpot)
            if (isNewBestSpot) prevBestSpot = spot
        }
        return maxScore
    }

    private fun getScore(values: IntArray, firstSpot: Int, secondSpot: Int): Int {
        require(firstSpot < secondSpot)
        return values[firstSpot] + values[secondSpot] + firstSpot - secondSpot
    }
}