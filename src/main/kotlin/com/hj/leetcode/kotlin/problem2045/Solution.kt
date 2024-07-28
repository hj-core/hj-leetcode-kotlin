package com.hj.leetcode.kotlin.problem2045

/**
 * LeetCode page: [2045. Second Minimum Time to Reach Destination](https://leetcode.com/problems/second-minimum-time-to-reach-destination/);
 */
class Solution {
    /* Complexity:
     * Time O(n+E) and Space O(n+E) where E is the size of edges;
     */
    fun secondMinimum(n: Int, edges: Array<IntArray>, time: Int, change: Int): Int {
        val adjacencyList = adjacencyList(edges)
        val bfsQueue = ArrayDeque<Int>()
        val firstVisit = IntArray(n + 1) { -1 } // firstVisit[i]::= first depth when visit i
        val secondVisit = IntArray(n + 1) { -1 } // secondVisit[i]::= second depth when visit i

        var depth = 0 // depth of nodes before each while-loop
        bfsQueue.addLast(1)
        firstVisit[1] = 0
        while (bfsQueue.isNotEmpty() && secondVisit[n] == -1) {
            depth++
            repeat(bfsQueue.size) {
                val node = bfsQueue.removeFirst()
                for (neighbour in (adjacencyList[node] ?: emptySet())) {
                    when {
                        firstVisit[neighbour] == -1 -> {
                            firstVisit[neighbour] = depth
                            bfsQueue.addLast(neighbour)

                        }

                        secondVisit[neighbour] == -1 && firstVisit[neighbour] != depth -> {
                            secondVisit[neighbour] = depth
                            bfsQueue.addLast(neighbour)
                        }
                    }
                }
            }
        }

        val secondMinLength = if (firstVisit[n] + 1 == secondVisit[n]) {
            secondVisit[n]
        } else {
            firstVisit[n] + 2
        }
        return traverseTime(secondMinLength, time, change)
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, Set<Int>> {
        val result = mutableMapOf<Int, MutableSet<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { mutableSetOf() }.add(v)
            result.computeIfAbsent(v) { mutableSetOf() }.add(u)
        }
        return result
    }

    private fun traverseTime(pathLength: Int, time: Int, change: Int): Int {
        var result = 0
        repeat(pathLength) {
            val numChanged = result / change
            val isRed = numChanged % 2 == 1
            if (isRed) {
                result = change * (numChanged + 1)
            }
            result += time
        }
        return result
    }
}