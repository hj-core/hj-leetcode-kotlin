package com.hj.leetcode.kotlin.problem2210

/**
 * LeetCode page: [2210. Count Hills and Valleys in an Array](https://leetcode.com/problems/count-hills-and-valleys-in-an-array/);
 */
class Solution {
    private var hillValleyCount = 0
    private var prevLevelTrend = LevelTrend.UNKNOWN

    private enum class LevelTrend { INCREASE, DECREASE, UNKNOWN }

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countHillValley(nums: IntArray): Int {
        for (index in 0 until nums.lastIndex) {
            val currLevelTrend = getLevelTrend(index, nums)
            updateCountHillValley(currLevelTrend)
            updatePrevLevelTrend(currLevelTrend)
        }
        return getHillValleyCount()
    }

    private fun getLevelTrend(indexOfLevel: Int, levels: IntArray): LevelTrend {
        return when {
            levels[indexOfLevel] > levels[indexOfLevel + 1] -> LevelTrend.DECREASE
            levels[indexOfLevel] < levels[indexOfLevel + 1] -> LevelTrend.INCREASE
            else -> LevelTrend.UNKNOWN
        }
    }

    private fun updateCountHillValley(currLevelTrend: LevelTrend) {
        when (currLevelTrend) {
            LevelTrend.INCREASE -> if (prevLevelTrend == LevelTrend.DECREASE) hillValleyCount++
            LevelTrend.DECREASE -> if (prevLevelTrend == LevelTrend.INCREASE) hillValleyCount++
            LevelTrend.UNKNOWN -> {}
        }
    }

    private fun updatePrevLevelTrend(currLevelTrend: LevelTrend) {
        if (currLevelTrend != LevelTrend.UNKNOWN) prevLevelTrend = currLevelTrend
    }

    private fun getHillValleyCount(): Int {
        val count = hillValleyCount
        resetStates()
        return count
    }

    private fun resetStates() {
        hillValleyCount = 0
        prevLevelTrend = LevelTrend.UNKNOWN
    }
}