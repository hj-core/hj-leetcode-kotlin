package com.hj.leetcode.kotlin.problem1061

/**
 * LeetCode page: [1061. Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/);
 */
class Solution {
    // Complexity:
    // Time O(S+N) and Space O(N) where S is the length of s1 and s2, N is
    // the length of baseStr.
    fun smallestEquivalentString(
        s1: String,
        s2: String,
        baseStr: String,
    ): String {
        val uf = UnionFind(26)
        for (i in s1.indices) {
            uf.union(s1[i] - 'a', s2[i] - 'a')
        }

        return buildString(baseStr.length) {
            for (c in baseStr) {
                append('a' + uf.find(c - 'a'))
            }
        }
    }

    private class UnionFind(
        size: Int,
    ) {
        private val root = IntArray(size) { it }

        fun find(x: Int): Int {
            if (root[x] != x) {
                root[x] = find(root[x])
            }
            return root[x]
        }

        fun union(
            x: Int,
            y: Int,
        ) {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX < rootY) {
                root[rootY] = rootX
            } else {
                root[rootX] = rootY
            }
        }
    }
}
