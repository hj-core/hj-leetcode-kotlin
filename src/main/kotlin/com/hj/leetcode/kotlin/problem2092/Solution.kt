package com.hj.leetcode.kotlin.problem2092

/**
 * LeetCode page: [2092. Find All People With Secret](https://leetcode.com/problems/find-all-people-with-secret/);
 */
class Solution {
    /* Complexity:
     * Time O(n+MLogM) and Space O(n+M) where M is the size of meetings;
     */
    fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
        val uf = UnionFind(n)
        uf.union(0, firstPerson)

        val sortedMeetings = meetings.sortedBy { it[2] }
        var start = 0
        while (start < sortedMeetings.size) {
            var end = start + 1
            while (end < sortedMeetings.size &&
                sortedMeetings[end][2] == sortedMeetings[start][2]
            ) {
                end++
            }

            for (i in start..<end) {
                uf.union(sortedMeetings[i][0], sortedMeetings[i][1])
            }
            for (i in start..<end) {
                if (uf.find(sortedMeetings[i][0]) != uf.find(0)) {
                    uf.weirdRest(sortedMeetings[i][0])
                    uf.weirdRest(sortedMeetings[i][1])
                }
            }

            start = end
        }
        return (0..<n).filter { uf.find(it) == uf.find(0) }
    }

    private class UnionFind(size: Int) {

        private val parent = IntArray(size) { it }
        private val rank = IntArray(size)

        fun find(x: Int): Int {
            if (x != parent[x]) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(x: Int, y: Int) {
            val xParent = find(x)
            val yParent = find(y)

            if (xParent == yParent) {
                return
            }

            when {
                rank[xParent] < rank[yParent] -> parent[xParent] = yParent
                rank[xParent] > rank[yParent] -> parent[yParent] = xParent
                else -> {
                    parent[xParent] = yParent
                    rank[yParent]++
                }
            }
        }

        fun weirdRest(x: Int) {
            parent[x] = x
            rank[x] = 0
        }
    }
}