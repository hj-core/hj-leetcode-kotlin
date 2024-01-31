package com.hj.leetcode.kotlin.problem739

/**
 * LeetCode page: [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N equals the size of temperature;
     */
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val noWarmer = 0
        val result = IntArray(temperatures.size) { noWarmer }

        for (i in temperatures.lastIndex - 1 downTo 0) {
            var warmer = i + 1
            while (result[warmer] != noWarmer && temperatures[i] >= temperatures[warmer]) {
                warmer += result[warmer]
            }

            result[i] = if (temperatures[i] < temperatures[warmer]) {
                warmer - i
            } else {
                noWarmer
            }
        }
        return result
    }
}