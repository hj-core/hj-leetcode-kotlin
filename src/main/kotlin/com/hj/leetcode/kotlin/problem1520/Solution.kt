package com.hj.leetcode.kotlin.problem1520

/**
 * LeetCode page: [1520. Maximum Number of Non-Overlapping Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/);
 */
class Solution {
    // Complexity:
    // Time O(N + (M^2)LogN + MLogM) and Space O(N+M) where N is the length of s
    // and M is the size of char set (i.e., 26).
    fun maxNumOfSubstrings(s: String): List<String> {
        val charIndices = Array(26) { mutableListOf<Int>() }
        for ((i, c) in s.withIndex()) {
            charIndices[c - 'a'].add(i)
        }

        val adjacencyList = Array(26) { mutableListOf<Int>() }
        for (i in 0..<26) {
            if (charIndices[i].isEmpty()) {
                continue
            }
            val first = charIndices[i].first()
            val last = charIndices[i].last()
            for (j in 0..<26) {
                if (charIndices[j].binarySearch(first) != charIndices[j].binarySearch(last)) {
                    adjacencyList[i].add(j)
                }
            }
        }

        val intervals = mutableListOf<IntArray>()
        for (i in 0..<26) {
            if (charIndices[i].isEmpty()) {
                continue
            }
            val interval = dfsInterval(i, adjacencyList, BooleanArray(26), charIndices)
            intervals.add(interval)
        }

        intervals.sortBy { it[0] }
        val selected = mutableListOf<IntArray>()
        for (interval in intervals) {
            while (selected.isNotEmpty() && interval[0] < selected.last()[1]) {
                selected.removeLast()
            }
            selected.add(interval)
        }

        return selected.map { (first, last) -> s.substring(first..last) }
    }

    private fun dfsInterval(
        c: Int,
        adjacencyList: Array<MutableList<Int>>,
        visited: BooleanArray,
        charIndices: Array<MutableList<Int>>,
    ): IntArray {
        visited[c] = true
        val interval = intArrayOf(charIndices[c].first(), charIndices[c].last())
        for (next in adjacencyList[c]) {
            if (visited[next] || charIndices[next].isEmpty()) {
                continue
            }
            val childInterval = dfsInterval(next, adjacencyList, visited, charIndices)
            interval[0] = minOf(interval[0], childInterval[0])
            interval[1] = maxOf(interval[1], childInterval[1])
        }
        return interval
    }
}
