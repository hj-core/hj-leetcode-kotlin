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

        // For each component, component[size-component[0]] is
        // the smallest online node in the component.
        val components = HashMap<Int, IntArray>()
        for (id in 1..c) {
            val root = uf.find(id)
            val comp =
                components.computeIfAbsent(root) {
                    IntArray(1 + uf.componentSize(root))
                }
            comp[0]++
            comp[comp[0]] = id
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

            val root = uf.find(id)
            val comp = checkNotNull(components[root])
            while (0 < comp[0] &&
                isOffline[comp[comp.size - comp[0]]]
            ) {
                comp[0]--
            }

            if (comp[0] == 0) {
                result.add(-1)
            } else {
                result.add(comp[comp.size - comp[0]])
            }
        }
        return result.toIntArray()
    }
}

private class UnionFind(
    size: Int,
) {
    private val root = IntArray(size) { it }
    private val size = IntArray(size) { 1 }

    fun componentSize(i: Int): Int = size[i]

    fun find(i: Int): Int {
        if (root[i] == i) {
            return i
        }
        root[i] = find(root[i])
        return root[i]
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

        if (size[rootI] < size[rootJ]) {
            root[rootI] = rootJ
            size[rootJ] += size[rootI]
        } else {
            root[rootJ] = rootI
            size[rootI] += size[rootJ]
        }
        return true
    }
}
