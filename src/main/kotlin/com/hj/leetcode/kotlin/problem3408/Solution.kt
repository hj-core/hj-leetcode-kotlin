package com.hj.leetcode.kotlin.problem3408

import java.util.*

/**
 * LeetCode page: [3408. Design Task Manager](https://leetcode.com/problems/design-task-manager/);
 */
class TaskManager(
    tasks: List<List<Int>>,
) {
    private class Task(
        val uid: Int,
        val tid: Int,
        var priority: Int,
    )

    private val tidToTask = hashMapOf<Int, Task>()
    private val taskPq = TreeSet<Task>(compareBy({ -it.priority }, { -it.tid }))

    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of tasks.
    init {
        for (task in tasks) {
            add(task[0], task[1], task[2])
        }
    }

    // Complexity for M calls:
    // Time O(Log(N+M)) and Space O(M) where N is the number of
    // existing tasks.
    fun add(
        userId: Int,
        taskId: Int,
        priority: Int,
    ) {
        val task = Task(userId, taskId, priority)
        tidToTask[task.tid] = task
        taskPq.add(task)
    }

    // Complexity:
    // Time O(LogN) and Space O(1) where N is the number of existing
    // tasks.
    fun edit(
        taskId: Int,
        newPriority: Int,
    ) {
        val task = checkNotNull(tidToTask[taskId])
        taskPq.remove(task)
        task.priority = newPriority
        taskPq.add(task)
    }

    // Complexity:
    // Time O(LogN) and Space O(1) where N is the number of existing
    // tasks.
    fun rmv(taskId: Int) {
        val task = checkNotNull(tidToTask[taskId])
        tidToTask.remove(taskId)
        taskPq.remove(task)
    }

    // Complexity:
    // Time O(LogN) and Space O(1) where N is the number of existing
    // tasks.
    fun execTop(): Int {
        val task = taskPq.pollFirst() ?: return -1
        tidToTask.remove(task.tid)
        return task.uid
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * var obj = TaskManager(tasks)
 * obj.add(userId,taskId,priority)
 * obj.edit(taskId,newPriority)
 * obj.rmv(taskId)
 * var param_4 = obj.execTop()
 */
