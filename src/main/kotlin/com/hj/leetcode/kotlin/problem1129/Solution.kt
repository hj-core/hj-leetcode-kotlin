package com.hj.leetcode.kotlin.problem1129

/**
 * LeetCode page: [1129. Shortest Path with Alternating Colors](https://leetcode.com/problems/shortest-path-with-alternating-colors/);
 */
class Solution {
    /* Complexity:
     * Time O(n + e) and Space (e) where e is the sum of redEdges' size and blueEdges' size;
     */
    fun shortestAlternatingPaths(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
        val redEdgeMap = buildEdgesInMap(redEdges)
        val blueEdgeMap = buildEdgesInMap(blueEdges)

        val redEndQueue = ArrayDeque<Int>()
        val blueEndQueue = ArrayDeque<Int>()

        val shortestDistance = IntArray(n) { -1 }
        var currDistance = 0
        redEndQueue.add(0)
        blueEndQueue.add(0)

        while (redEndQueue.isNotEmpty() || blueEndQueue.isNotEmpty()) {
            val numRedEnds = redEndQueue.size
            val numBlueEnds = blueEndQueue.size

            repeat(numRedEnds) {
                val redEnd = redEndQueue.removeFirst()
                if (shortestDistance[redEnd] == -1) {
                    shortestDistance[redEnd] = currDistance
                }

                val nextBlueEnds = blueEdgeMap[redEnd] ?: mutableListOf()
                for (blueEnd in nextBlueEnds) {
                    blueEndQueue.addLast(blueEnd)
                }
                blueEdgeMap.remove(redEnd)
            }

            repeat(numBlueEnds) {
                val blueEnd = blueEndQueue.removeFirst()
                if (shortestDistance[blueEnd] == -1) {
                    shortestDistance[blueEnd] = currDistance
                }

                val nextRedEnds = redEdgeMap[blueEnd] ?: mutableListOf()
                for (redEnd in nextRedEnds) {
                    redEndQueue.addLast(redEnd)
                }
                redEdgeMap.remove(blueEnd)
            }

            currDistance++
        }

        return shortestDistance
    }

    private fun buildEdgesInMap(edges: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val e = hashMapOf<Int, MutableList<Int>>()
        for ((u, v) in edges) {
            e.computeIfAbsent(u) { mutableListOf() }.add(v)
        }
        return e
    }
}