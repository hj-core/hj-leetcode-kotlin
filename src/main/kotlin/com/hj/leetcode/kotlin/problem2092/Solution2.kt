package com.hj.leetcode.kotlin.problem2092

/**
 * LeetCode page: [2092. Find All People With Secret](https://leetcode.com/problems/find-all-people-with-secret/);
 */
class Solution2 {
    // Complexity:
    // Time O(n+MLogM) and Space O(n+M) where M is the size of
    // meetings.
    fun findAllPeople(
        n: Int,
        meetings: Array<IntArray>,
        firstPerson: Int,
    ): List<Int> {
        val learned = BooleanArray(n)
        learned[0] = true
        learned[firstPerson] = true

        val sortedMeetings = meetings.sortedBy { it[2] }
        val adjacencyList = List(n) { mutableListOf<Int>() }

        var end = 0
        while (end < sortedMeetings.size) {
            val start = end
            val t = sortedMeetings[end][2]

            // Build the adjacency list for current meetings
            while (end < sortedMeetings.size &&
                sortedMeetings[end][2] == t
            ) {
                val (x, y, _) = sortedMeetings[end]
                adjacencyList[x].add(y)
                adjacencyList[y].add(x)
                end++
            }

            // Propagate the secrete
            for (j in start..<end) {
                val (x, y, _) = sortedMeetings[j]
                if (learned[x] != learned[y]) {
                    share(x, adjacencyList, learned)
                    share(y, adjacencyList, learned)
                }
            }

            // Reset the adjacency list
            for (j in start..<end) {
                val (x, y, _) = sortedMeetings[j]
                adjacencyList[x].clear()
                adjacencyList[y].clear()
            }
        }

        return (0..<n).filter { learned[it] }
    }

    // Propagates the secret to root and all people reachable from
    // root if root has not yet learned the secret.
    private fun share(
        root: Int,
        adjacencyList: List<List<Int>>,
        learned: BooleanArray,
    ) {
        if (learned[root]) {
            return
        }

        learned[root] = true
        for (next in adjacencyList[root]) {
            share(next, adjacencyList, learned)
        }
    }
}
