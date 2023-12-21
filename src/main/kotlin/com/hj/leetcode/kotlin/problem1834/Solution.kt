package com.hj.leetcode.kotlin.problem1834

import java.util.*

/**
 * LeetCode page: [1834. Single-Threaded CPU](https://leetcode.com/problems/single-threaded-cpu/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of tasks;
     */
    fun getOrder(tasks: Array<IntArray>): IntArray {
        val sortedTasks = sortedTasksByEnqueueTime(tasks)
        val taskComparator = compareBy<Task>({ it.processingTime }, { it.index })
        val taskPq = PriorityQueue(taskComparator)
        var currentTime = 0
        var nextOfferIndex = 0 // first index at sortedTasks that not yet offered to taskPq;
        val order = IntArray(tasks.size)
        var nextAssignIndex = 0 // index at order that current task should assign to;

        while (nextAssignIndex < sortedTasks.size) {
            while (nextOfferIndex < sortedTasks.size &&
                sortedTasks[nextOfferIndex].enqueueTime <= currentTime
            ) {
                taskPq.offer(sortedTasks[nextOfferIndex])
                nextOfferIndex++
            }

            if (taskPq.isEmpty()) {
                currentTime = sortedTasks[nextOfferIndex].enqueueTime
                continue
            }

            val executeTask = taskPq.poll()
            order[nextAssignIndex] = executeTask.index
            nextAssignIndex++
            currentTime += executeTask.processingTime
        }

        return order
    }

    private fun sortedTasksByEnqueueTime(tasks: Array<IntArray>): List<Task> {
        val tasksList = MutableList(tasks.size) { index ->
            Task(index, tasks[index][0], tasks[index][1])
        }
        tasksList.sortBy { task -> task.enqueueTime }
        return tasksList
    }

    private data class Task(val index: Int, val enqueueTime: Int, val processingTime: Int)
}