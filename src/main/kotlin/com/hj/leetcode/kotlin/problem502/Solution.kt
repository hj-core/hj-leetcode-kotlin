package com.hj.leetcode.kotlin.problem502

import java.util.*

/**
 * LeetCode page: [502. IPO](https://leetcode.com/problems/ipo/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of capital/profits;
     */
    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        val sortedProjects = capital
            .indices
            .mapTo(mutableListOf()) { Project(profits[it], capital[it]) }
            .apply { sortBy { it.requiredCapital } }

        var result = w
        val availableProfits = PriorityQueue<Int>(reverseOrder())
        var nextIndex = 0

        repeat(k) {
            while (nextIndex < sortedProjects.size
                && sortedProjects[nextIndex].requiredCapital <= result
            ) {
                availableProfits.offer(sortedProjects[nextIndex].profit)
                nextIndex++
            }

            if (availableProfits.isEmpty()) {
                return result
            }
            result += availableProfits.poll()
        }
        return result
    }

    private data class Project(val profit: Int, val requiredCapital: Int)
}