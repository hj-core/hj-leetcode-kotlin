package com.hj.leetcode.kotlin.problem1319

/**
 * LeetCode page: [1319. Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/);
 */
class Solution {
    /* Complexity:
     * Time O(E) and Space O(E) where E is the size of connections;
     */
    fun makeConnected(n: Int, connections: Array<IntArray>): Int {
        val numCables = connections.size
        if (numCables < n - 1) return -1

        val adjacent = adjacent(n, connections)
        val numConnectedComponents = numConnectedComponents(n, adjacent)
        return numConnectedComponents - 1
    }

    private fun adjacent(numComputers: Int, connections: Array<IntArray>): List<List<Int>> {
        val adjacent = List<MutableList<Int>>(numComputers) { mutableListOf() }
        for ((u, v) in connections) {
            adjacent[u].add(v)
            adjacent[v].add(u)
        }
        return adjacent
    }

    private fun numConnectedComponents(numComputers: Int, adjacent: List<List<Int>>): Int {
        var numComponents = 0
        val visited = hashSetOf<Int>()
        val computers = 0 until numComputers
        for (computer in computers) {
            if (computer in visited) {
                continue
            }
            numComponents++
            visitAllReachableComputers(computer, adjacent, visited)
        }
        return numComponents
    }

    private fun visitAllReachableComputers(
        sourceComputer: Int,
        adjacent: List<List<Int>>,
        visited: MutableSet<Int>
    ) {
        if (sourceComputer in visited) {
            return
        }
        visited.add(sourceComputer)
        for (label in adjacent[sourceComputer]) {
            visitAllReachableComputers(label, adjacent, visited)
        }
    }
}