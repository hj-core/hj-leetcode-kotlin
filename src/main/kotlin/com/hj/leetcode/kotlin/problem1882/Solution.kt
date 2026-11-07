package com.hj.leetcode.kotlin.problem1882

import java.util.PriorityQueue

/**
 * LeetCode page: [1882. Process Tasks Using Servers](https://leetcode.com/problems/process-tasks-using-servers/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN+MLogN) and Space O(N) where N is the length of servers
    // and M is the length of tasks.
    fun assignTasks(
        servers: IntArray,
        tasks: IntArray
    ): IntArray {
        val idle = PriorityQueue<IntArray>(compareBy({ (id, weight) -> weight }, { it[0] }))
        val working = PriorityQueue<Pair<IntArray, Long>>(compareBy { (server, finish) -> finish })
        val answer = IntArray(tasks.size)
        var t = 0L
        for (id in servers.indices) {
            idle.offer(intArrayOf(id, servers[id]))
        }

        for ((j, taskTime) in tasks.withIndex()) {
            if (idle.isEmpty()) {
                t = working.peek().second
            }
            t = maxOf(t, j.toLong())
            while (working.isNotEmpty() && working.peek().second <= t) {
                val (server, _) = working.poll()
                idle.offer(server)
            }
            val server = idle.poll()
            answer[j] = server[0]
            working.offer(Pair(server, t + taskTime))
        }

        return answer
    }
}
