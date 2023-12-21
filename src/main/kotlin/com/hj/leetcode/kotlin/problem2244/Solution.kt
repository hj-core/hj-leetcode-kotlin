package com.hj.leetcode.kotlin.problem2244

/**
 * LeetCode page: [2244. Minimum Rounds to Complete All Tasks](https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/);
 */
class Solution {
    /* Complexity:
     * Time O(|tasks|) and Space O(|tasks|);
     */
    fun minimumRounds(tasks: IntArray): Int {
        val counts = countEachDifficulty(tasks)
        var minRounds = 0
        for (count in counts.values) {
            if (count == 1) return -1
            /* when count is not equal to 1, we will have the following cases:
             *   1) count = 3k + 1 then k < task.minRound <= k + 1 and task.minRound = 3 * (k - 1) + 2 * 2;
             *   2) count = 3k + 2 then k < task.minRound <= k + 1 and task.minRound = 3 * (k    ) + 2 * 1;
             *   3) count = 3k + 3 then k < task.minRound <= k + 1 and task.minRound = 3 * (k + 1) + 2 * 0;
             * where k is non-negative integer.
             * task.minRound = (count + 2) / 3 is a shortcut for the above three cases.
             */
            minRounds += (count + 2) / 3
        }
        return minRounds
    }

    private fun countEachDifficulty(tasks: IntArray): Map<Int, Int> {
        val counts = hashMapOf<Int, Int>()
        for (task in tasks) {
            counts[task] = 1 + (counts[task] ?: 0)
        }
        return counts
    }
}
