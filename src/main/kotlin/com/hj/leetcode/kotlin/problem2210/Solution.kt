package com.hj.leetcode.kotlin.problem2210

/**
 * LeetCode page: [2210. Count Hills and Valleys in an Array](https://leetcode.com/problems/count-hills-and-valleys-in-an-array/);
 */
class Solution {
    private enum class LevelTrend { INCREASE, DECREASE, UNKNOWN }

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countHillValley(nums: IntArray): Int {
        var hillValleyCount = 0
        var prevLevelTrend = LevelTrend.UNKNOWN

        for (index in 0 until nums.lastIndex) {
            val currLevelTrend = getLevelTrend(index, nums)
            if (isHillOrValley(currLevelTrend, prevLevelTrend)) hillValleyCount++
            if (currLevelTrend != LevelTrend.UNKNOWN) prevLevelTrend = currLevelTrend
        }

        return hillValleyCount
    }

    private fun getLevelTrend(indexOfLevel: Int, levels: IntArray): LevelTrend {
        return when {
            levels[indexOfLevel] > levels[indexOfLevel + 1] -> LevelTrend.DECREASE
            levels[indexOfLevel] < levels[indexOfLevel + 1] -> LevelTrend.INCREASE
            else -> LevelTrend.UNKNOWN
        }
    }

    private fun isHillOrValley(currLevelTrend: LevelTrend, prevLevelTrend: LevelTrend): Boolean {
        return when (currLevelTrend) {
            LevelTrend.INCREASE -> prevLevelTrend == LevelTrend.DECREASE
            LevelTrend.DECREASE -> prevLevelTrend == LevelTrend.INCREASE
            LevelTrend.UNKNOWN -> false
        }
    }
}