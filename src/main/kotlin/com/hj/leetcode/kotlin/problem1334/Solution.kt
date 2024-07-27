package com.hj.leetcode.kotlin.problem1334

import kotlin.math.min

/**
 * LeetCode page: [1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/);
 */
class Solution {
    /* Complexity:
     * Time (n^3) and Space O(n^2);
     */
    fun findTheCity(n: Int, edges: Array<IntArray>, distanceThreshold: Int): Int {
        val distanceMatrix = floydWarshall(n, edges)
        val countReachable = countReachableNeighbors(n, distanceMatrix, distanceThreshold)
        val minCount = countReachable.min()
        return (n - 1 downTo 0).first { countReachable[it] == minCount }
    }

    private fun floydWarshall(n: Int, edges: Array<IntArray>): Array<IntArray> {
        val result = getAdjacencyMatrix(n, edges)
        for (k in 0..<n) {
            for (i in 0..<n) {
                for (j in 0..<n) {
                    result[i][j] = if (result[i][k] == Int.MAX_VALUE || result[k][j] == Int.MAX_VALUE) {
                        result[i][j]
                    } else {
                        min(result[i][j], result[i][k] + result[k][j])
                    }
                }
            }
        }
        return result
    }

    private fun getAdjacencyMatrix(n: Int, edges: Array<IntArray>): Array<IntArray> {
        val result = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        for (i in 0..<n) {
            result[i][i] = 0
        }
        for ((u, v, weight) in edges) {
            result[u][v] = weight
            result[v][u] = weight
        }
        return result
    }

    private fun countReachableNeighbors(
        n: Int,
        distanceMatrix: Array<IntArray>,
        distanceThreshold: Int,
    ): IntArray {
        val result = IntArray(n)
        for ((city, neighbourDistances) in distanceMatrix.withIndex()) {
            result[city] = neighbourDistances.count { it <= distanceThreshold }
        }
        return result
    }
}