package com.hj.leetcode.kotlin.problem3607

/**
 * LeetCode page: [3607. Power Grid Maintenance](https://leetcode.com/problems/power-grid-maintenance/);
 */
class Solution {
    // Complexity:
    // Time O(c+N+M) and Space O(c+M) where N and M are the
    // length of connections and queries, respectively.
    fun processQueries(
        c: Int,
        connections: Array<IntArray>,
        queries: Array<IntArray>,
    ): IntArray {
        val uf = UnionFind(c + 1)
        for ((u, v) in connections) {
            uf.union(u, v)
        }

        // For each group, group[group.size-group[0]] is the
        // smallest online node in the group.
        val groups = HashMap<Int, IntArray>()
        for (id in 1..c) {
            val parent = uf.find(id)
            val group =
                groups.computeIfAbsent(parent) {
                    IntArray(1 + uf.componentSize(parent))
                }
            group[0]++
            group[group[0]] = id
        }

        val result = mutableListOf<Int>()
        val isOffline = BooleanArray(c + 1)

        for ((type, id) in queries) {
            if (type == 2) {
                isOffline[id] = true
                continue
            }

            if (!isOffline[id]) {
                result.add(id)
                continue
            }

            val parent = uf.find(id)
            val group = checkNotNull(groups[parent])
            while (0 < group[0] &&
                isOffline[group[group.size - group[0]]]
            ) {
                group[0]--
            }

            if (group[0] == 0) {
                result.add(-1)
            } else {
                result.add(group[group.size - group[0]])
            }
        }
        return result.toIntArray()
    }
}

private class UnionFind(
    size: Int,
) {
    private val parent = IntArray(size) { it }
    private val componentSize = IntArray(size) { 1 }

    fun find(i: Int): Int {
        if (parent[i] == i) {
            return i
        }
        parent[i] = find(parent[i])
        return parent[i]
    }

    fun union(
        i: Int,
        j: Int,
    ): Boolean {
        val rootI = find(i)
        val rootJ = find(j)
        if (rootI == rootJ) {
            return false
        }

        if (componentSize[rootI] < componentSize[rootJ]) {
            parent[rootI] = rootJ
            componentSize[rootJ] += componentSize[rootI]
        } else {
            parent[rootJ] = rootI
            componentSize[rootI] += componentSize[rootJ]
        }
        return true
    }

    fun componentSize(i: Int): Int = componentSize[i]
}
