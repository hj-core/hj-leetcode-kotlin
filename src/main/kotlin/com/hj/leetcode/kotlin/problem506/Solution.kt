package com.hj.leetcode.kotlin.problem506

/**
 * LeetCode page: [506. Relative Ranks](https://leetcode.com/problems/relative-ranks/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of score;
     */
    fun findRelativeRanks(score: IntArray): Array<String> {
        val sortedAthletes = score.indices.sortedBy { -score[it] }
        val medals = listOf("Gold Medal", "Silver Medal", "Bronze Medal")
        val result = Array(score.size) { "" }

        for ((index, athlete) in sortedAthletes.withIndex()) {
            result[athlete] = if (index < medals.size) {
                medals[index]
            } else {
                (index + 1).toString()
            }
        }
        return result
    }
}