package com.hj.leetcode.kotlin.problem2092

/**
 * LeetCode page: [2092. Find All People With Secret](https://leetcode.com/problems/find-all-people-with-secret/);
 */
class Solution {
    // Complexity:
    // Time O(n+MLogM) and Space O(n+M) where M is the size of
    // meetings.
    fun findAllPeople(
        n: Int,
        meetings: Array<IntArray>,
        firstPerson: Int,
    ): List<Int> {
        val sortedMeetings = meetings.sortedBy { it[2] }
        val uf = UnionFind(n)
        uf.union(0, firstPerson)

        var end = 0
        while (end < sortedMeetings.size) {
            val start = end
            val t = sortedMeetings[end][2]

            // Blindly connect attendees to the network
            while (end < sortedMeetings.size &&
                sortedMeetings[end][2] == t
            ) {
                val (x, y, _) = sortedMeetings[end]
                uf.union(x, y)
                end++
            }

            // Every one who is not in the right network disconnects
            // himself from his parent. At the end, every one who
            // does not know the secret becomes alone again.
            for (j in start..<end) {
                val (x, y, _) = sortedMeetings[j]
                if (uf.find(x) != uf.find(0)) {
                    uf.weirdReset(x)
                    uf.weirdReset(y)
                }
            }
        }

        return (0..<n).filter { uf.find(it) == uf.find(0) }
    }

    private class UnionFind(
        size: Int,
    ) {
        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)

        fun find(x: Int): Int {
            if (x != parent[x]) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(
            x: Int,
            y: Int,
        ) {
            val xParent = find(x)
            val yParent = find(y)
            if (xParent == yParent) {
                return
            }

            val cmpRank = rank[xParent] - rank[yParent]
            when {
                cmpRank < 0 -> {
                    parent[xParent] = yParent
                }

                cmpRank > 0 -> {
                    parent[yParent] = xParent
                }

                else -> {
                    parent[xParent] = yParent
                    rank[yParent]++
                }
            }
        }

        fun weirdReset(x: Int) {
            parent[x] = x
            rank[x] = 0
        }
    }
}
