package com.hj.leetcode.kotlin.problem1488

/**
 * LeetCode page: [1488. Avoid Flood in The City](https://leetcode.com/problems/avoid-flood-in-the-city/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(N) where N is the size of rains.
    fun avoidFlood(rains: IntArray): IntArray {
        // UnionFind
        val parent = IntArray(rains.size + 1) { rains.size }

        fun find(x: Int): Int {
            var xp = x
            while (parent[xp] != xp) {
                xp = parent[xp]
            }
            var y = x
            while (parent[y] != xp) {
                y = parent[y].also { parent[y] = xp }
            }
            return xp
        }

        // lastFullDay[lake]:= the previous day that lake became full
        val lastFullDay = hashMapOf<Int, Int>()
        var lastDryDay = -1
        val result = IntArray(rains.size) { 1 }

        for ((i, rain) in rains.withIndex()) {
            when (rain) {
                0 -> {
                    for (j in i downTo lastDryDay + 1) {
                        parent[j] = i
                    }
                    if (lastDryDay >= 0 && parent[lastDryDay] == rains.size) {
                        parent[lastDryDay] = i
                    }
                    lastDryDay = i
                }

                in lastFullDay -> {
                    val fullDay = lastFullDay[rain]!!
                    val dryDay = find(fullDay)
                    if (dryDay == rains.size) {
                        return intArrayOf()
                    }

                    result[i] = -1
                    lastFullDay[rain] = i
                    result[dryDay] = rain
                    parent[dryDay] = parent[dryDay + 1]
                }

                else -> {
                    result[i] = -1
                    lastFullDay[rain] = i
                }
            }
        }
        return result
    }
}
