package com.hj.leetcode.kotlin.problem2747

/**
 * LeetCode page: [2747. Count Zero Request Servers](https://leetcode.com/problems/count-zero-request-servers/);
 */
class Solution {
    // Complexity:
    // Time O(n+NLogN+MLogM) and Space O(n+N+M) where N is the length of logs
    // and M is the length of queries.
    fun countServers(
        n: Int,
        logs: Array<IntArray>,
        x: Int,
        queries: IntArray,
    ): IntArray {
        val logs = logs.sortedArrayWith(compareBy { (serverId, time) -> time })
        val queries = Array(queries.size) { intArrayOf(it, queries[it]) }
        queries.sortBy { (index, time) -> time }

        val answer = IntArray(queries.size)
        val requestCount = IntArray(n + 1)
        var idle = n
        var left = 0
        var right = 0
        for ((index, finish) in queries) {
            while (right < logs.size && logs[right][1] <= finish) {
                val serverId = logs[right][0]
                requestCount[serverId]++
                if (requestCount[serverId] == 1) {
                    idle--
                }
                right++
            }

            val start = finish - x
            while (left < logs.size && logs[left][1] < start) {
                val serverId = logs[left][0]
                requestCount[serverId]--
                if (requestCount[serverId] == 0) {
                    idle++
                }
                left++
            }

            answer[index] = idle
        }

        return answer
    }
}
