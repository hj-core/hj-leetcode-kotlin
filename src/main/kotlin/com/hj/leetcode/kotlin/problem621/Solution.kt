package com.hj.leetcode.kotlin.problem621

/**
 * LeetCode page: [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/);
 */
class Solution {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val counter = tasks.asIterable().groupingBy { it }.eachCountTo(hashMapOf())
        val availableCycles = counter.keys.associateWithTo(hashMapOf()) { 0 }
        var result = 0
        while (counter.keys.isNotEmpty()) {
            val nextTask = counter
                .keys
                .filter { checkNotNull(availableCycles[it]) <= result }
                .maxByOrNull { checkNotNull(counter[it]) }

            if (nextTask == null) {
                result = availableCycles.values.min()
            } else {
                counter[nextTask] = checkNotNull(counter[nextTask]) - 1
                availableCycles[nextTask] = result + n + 1
                if (checkNotNull(counter[nextTask]) == 0) {
                    counter.remove(nextTask)
                    availableCycles.remove(nextTask)
                }
                result += 1
            }
        }
        return result
    }
}