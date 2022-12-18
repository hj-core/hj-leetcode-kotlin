package com.hj.leetcode.kotlin.problem739

/**
 * LeetCode page: [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N equals the size of temperature;
     */
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val daysWhenNoWarmer = 0
        val daysToNextWarmer = IntArray(temperatures.size).apply {
            this[lastIndex] = daysWhenNoWarmer
        }
        for (index in temperatures.lastIndex - 1 downTo 0) {
            var warmerIndex = index + 1
            while (temperatures[index] >= temperatures[warmerIndex]) {
                val noWarmerDay = daysToNextWarmer[warmerIndex] == daysWhenNoWarmer
                if (noWarmerDay) {
                    warmerIndex = index + daysWhenNoWarmer
                    break
                }
                warmerIndex += daysToNextWarmer[warmerIndex]
            }
            daysToNextWarmer[index] = warmerIndex - index
        }
        return daysToNextWarmer
    }
}