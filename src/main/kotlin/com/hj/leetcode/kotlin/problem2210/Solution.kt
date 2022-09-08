package com.hj.leetcode.kotlin.problem2210

/**
 * LeetCode page: [2210. Count Hills and Valleys in an Array](https://leetcode.com/problems/count-hills-and-valleys-in-an-array/);
 */
class Solution {

    private enum class LevelChange { INCREASE, DECREASE, EQUAL }

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun countHillValley(nums: IntArray): Int {
        var count = 0
        var prevLevelTrend = LevelChange.EQUAL
        for (index in 1..nums.lastIndex) {
            val currLevelChange = getLevelChange(nums, index)
            if (isTurningPoint(prevLevelTrend, currLevelChange)) count++
            if (currLevelChange != LevelChange.EQUAL) prevLevelTrend = currLevelChange
        }
        return count
    }

    private fun getLevelChange(levels: IntArray, index: Int): LevelChange {
        return when {
            levels[index] > levels[index - 1] -> LevelChange.INCREASE
            levels[index] < levels[index - 1] -> LevelChange.DECREASE
            else -> LevelChange.EQUAL
        }
    }

    private fun isTurningPoint(prevLevelTrend: LevelChange, currLevelChange: LevelChange): Boolean {
        return when (currLevelChange) {
            LevelChange.INCREASE -> prevLevelTrend == LevelChange.DECREASE
            LevelChange.DECREASE -> prevLevelTrend == LevelChange.INCREASE
            LevelChange.EQUAL -> false
        }
    }
}