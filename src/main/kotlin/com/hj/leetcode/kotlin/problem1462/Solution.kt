package com.hj.leetcode.kotlin.problem1462

/**
 * LeetCode page: [1462. Course Schedule IV](https://leetcode.com/problems/course-schedule-iv/);
 */
class Solution {
    /* Complexity:
     * Time O(V^3+N) and Auxiliary Space O(V^2)
     * where V is numCourses and N is the length of queries.
     */
    fun checkIfPrerequisite(
        numCourses: Int,
        prerequisites: Array<IntArray>,
        queries: Array<IntArray>,
    ): List<Boolean> {
        val v = numCourses
        val e = prerequisites.size
        val n = queries.size

        return if (n * e < (v / 4) * v * v) {
            dfs(numCourses, prerequisites, queries)
        } else {
            floydWarshall(numCourses, prerequisites, queries)
        }
    }

    /* Complexity:
     * Time O(V+NE) and Auxiliary Space O(V+E)
     * where V is numCourses, E is the length of prerequisites and N is the length of queries.
     */
    private fun dfs(
        numCourses: Int,
        prerequisites: Array<IntArray>,
        queries: Array<IntArray>,
    ): List<Boolean> {
        val adjacencyList = adjacencyList(numCourses, prerequisites)
        return queries.map { (src, dst) -> isReachable(src, dst, adjacencyList, mutableSetOf()) }
    }

    private fun adjacencyList(
        numCourses: Int,
        prerequisites: Array<IntArray>,
    ): List<List<Int>> {
        val result = List(numCourses) { mutableListOf<Int>() }
        for ((u, v) in prerequisites) {
            result[u].add(v)
        }
        return result
    }

    private fun isReachable(
        src: Int,
        dst: Int,
        adjacencyList: List<List<Int>>,
        visited: MutableSet<Int>,
    ): Boolean {
        if (src == dst) {
            return true
        }
        if (src in visited) {
            return false
        }
        visited.add(src)
        for (next in adjacencyList[src]) {
            if (isReachable(next, dst, adjacencyList, visited)) {
                return true
            }
        }
        return false
    }

    /* Complexity:
     * Time O(V^3+N) and Auxiliary Space O(V^2)
     * where V is numCourses and N is the length of queries.
     */
    private fun floydWarshall(
        numCourses: Int,
        prerequisites: Array<IntArray>,
        queries: Array<IntArray>,
    ): List<Boolean> {
        val reachability = Array(numCourses) { BooleanArray(numCourses) }
        for ((u, v) in prerequisites) {
            reachability[u][v] = true
        }

        for (mid in 0..<numCourses) {
            for (src in 0..<numCourses) {
                for (dst in 0..<numCourses) {
                    if (reachability[src][mid] && reachability[mid][dst]) {
                        reachability[src][dst] = true
                    }
                }
            }
        }
        return queries.map { (u, v) -> reachability[u][v] }
    }
}
