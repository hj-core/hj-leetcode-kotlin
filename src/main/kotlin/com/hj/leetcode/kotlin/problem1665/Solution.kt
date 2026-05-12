package com.hj.leetcode.kotlin.problem1665

/**
 * LeetCode page: [1665. Minimum Initial Energy to Finish Tasks](https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of tasks.
    fun minimumEffort(tasks: Array<IntArray>): Int {
        val sortedTasks = tasks.sortedArrayWith { a, b -> (b[1] - b[0]) - (a[1] - a[0]) }
        var totalEffort = 0
        var currEffort = 0
        for ((actual, required) in sortedTasks) {
            if (currEffort < required) {
                totalEffort += required - currEffort
                currEffort = required
            }
            currEffort -= actual
        }
        return totalEffort
    }
}
