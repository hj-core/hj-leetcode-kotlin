package com.hj.leetcode.kotlin.problem2071

/**
 * LeetCode page: [2071. Maximum Number of Tasks You Can Assign](https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN+MLogM) and Space O(N+M) where N and M are the length of
    // tasks and workers, respectively.
    fun maxTaskAssign(
        tasks: IntArray,
        workers: IntArray,
        pills: Int,
        strength: Int,
    ): Int {
        val sortedTasks = tasks.clone().apply { sort() }
        val sortedWorkers = workers.clone().apply { sort() }

        // Binary search on the maximum number of tasks that can be completed.
        // The result is in range [low-1, high].
        var low = 1
        var high = minOf(tasks.size, workers.size)

        while (low <= high) {
            val mid = (low + high) ushr 1
            if (canCompleteKTasks(sortedTasks, sortedWorkers, mid, pills, strength)) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        return high
    }

    private fun canCompleteKTasks(
        sortedTasks: IntArray,
        sortedWorkers: IntArray,
        k: Int,
        pills: Int,
        strength: Int,
    ): Boolean {
        // A deque of tasks that the current worker can take if he is powered with a pill,
        // and a pointer to the next task to be queued.
        val taskDeque = ArrayDeque<Int>()
        var taskPtr = 0
        var pillCnt = 0

        for (workerPtr in (sortedWorkers.size - k)..<sortedWorkers.size) {
            val workPower = sortedWorkers[workerPtr]

            while (taskPtr < k && sortedTasks[taskPtr] - workPower <= strength) {
                taskDeque.addLast(sortedTasks[taskPtr])
                taskPtr++
            }

            when {
                taskDeque.isEmpty() -> return false
                taskDeque.first() <= workPower -> {
                    taskDeque.removeFirst()
                }

                pills == pillCnt -> return false
                else -> {
                    taskDeque.removeLast()
                    pillCnt++
                }
            }
        }
        return true
    }
}
