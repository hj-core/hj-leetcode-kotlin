package com.hj.leetcode.kotlin.problem1335

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1335. Minimum Difficulty of a Job Schedule](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/);
 *
 * TODO : There is solution with time complexity O(Nd) [(see Ref)](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490316/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2 * d) and Space O(N * d) where N is the size of jobDifficulty;
     */
    fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
        if (jobDifficulty.size < d) {
            return -1
        }
        return minDifficultyDp(jobDifficulty, 0, d, hashMapOf())
    }

    private fun minDifficultyDp(
        jobDifficulty: IntArray,
        start: Int,
        d: Int,
        memoization: MutableMap<DpState, Int>,
    ): Int {
        if (start + d > jobDifficulty.size) {
            throw IllegalArgumentException()
        }

        val state = DpState(start, d)
        if (state in memoization) {
            return checkNotNull(memoization[state])
        }
        if (d == 1) {
            val result = (start..<jobDifficulty.size).maxOf { jobDifficulty[it] }
            memoization[state] = result
            return result
        }

        var result = Int.MAX_VALUE
        var firstDayDifficulty = jobDifficulty[start]
        for (firstDayEnd in start..<jobDifficulty.size - (d - 1)) {
            firstDayDifficulty = max(firstDayDifficulty, jobDifficulty[firstDayEnd])
            val currentMinDifficulty = firstDayDifficulty + minDifficultyDp(
                jobDifficulty,
                firstDayEnd + 1,
                d - 1,
                memoization
            )
            result = min(result, currentMinDifficulty)
        }
        memoization[state] = result
        return result
    }

    private data class DpState(val start: Int, val d: Int)
}