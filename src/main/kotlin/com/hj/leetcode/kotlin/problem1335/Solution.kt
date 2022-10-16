package com.hj.leetcode.kotlin.problem1335

/**
 * LeetCode page: [1335. Minimum Difficulty of a Job Schedule](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/);
 *
 * TODO : There is solution with time complexity O(Nd) [(see Ref)](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490316/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2 * d) and Space O(N) where N is the size of jobDifficulty;
     */
    fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
        val noEnoughJobs = jobDifficulty.size < d
        if (noEnoughJobs) return -1

        val dp = containerOfSubArrayMinDifficultyPerEndIndex(jobDifficulty.size)
        updateSubArrayMinDifficultyPerEndIndex(dp, d, jobDifficulty)

        return dp[jobDifficulty.lastIndex]
    }

    private fun containerOfSubArrayMinDifficultyPerEndIndex(totalJobs: Int): IntArray {
        return IntArray(totalJobs)
    }

    private fun updateSubArrayMinDifficultyPerEndIndex(container: IntArray, totalDays: Int, jobDifficulty: IntArray) {
        updateForSingleDayCase(container, jobDifficulty)
        furtherUpdateToTotalDays(container, jobDifficulty, totalDays)
    }

    private fun updateForSingleDayCase(container: IntArray, jobDifficulty: IntArray) {
        container[0] = jobDifficulty[0]

        for (index in 1..container.lastIndex) {
            container[index] = maxOf(container[index - 1], jobDifficulty[index])
        }
    }

    private fun furtherUpdateToTotalDays(container: IntArray, jobDifficulty: IntArray, totalDays: Int) {
        for (currDays in 1 until totalDays) {

            for (endIndex in container.lastIndex downTo currDays) {
                var newDayMaxDifficulty = jobDifficulty[endIndex]
                var currDayPrevIndexMinDifficulty = container[endIndex - 1]
                var minDifficulty = newDayMaxDifficulty + currDayPrevIndexMinDifficulty

                for (index in endIndex - 1 downTo currDays) {
                    newDayMaxDifficulty = maxOf(newDayMaxDifficulty, jobDifficulty[index])
                    currDayPrevIndexMinDifficulty = container[index - 1]
                    minDifficulty = minOf(minDifficulty, newDayMaxDifficulty + currDayPrevIndexMinDifficulty)
                }

                container[endIndex] = minDifficulty
            }

            container[currDays - 1] = -1
        }
    }
}