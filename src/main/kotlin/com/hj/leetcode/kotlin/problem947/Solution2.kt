package com.hj.leetcode.kotlin.problem947

/**
 * LeetCode page: [947. Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of stones;
     */
    fun removeStones(stones: Array<IntArray>): Int {
        val uf = UnionFind(stones.size)
        var numComponents = stones.size

        val groupsByX = stones.indices.groupBy { stones[it][0] }
        val groupsByY = stones.indices.groupBy { stones[it][1] }
        for (groupsBy in listOf(groupsByX, groupsByY)) {
            for ((_, group) in groupsBy) {
                val root = group.first()
                for (other in group) {
                    if (uf.union(root, other)) {
                        numComponents--
                    }
                }
            }
        }
        return stones.size - numComponents
    }

    private class UnionFind(
        n: Int,
    ) {
        val parents = IntArray(n) { it }
        val ranks = IntArray(n)

        fun findSet(x: Int): Int {
            if (x != parents[x]) {
                parents[x] = findSet(parents[x])
            }
            return parents[x]
        }

        fun union(
            x: Int,
            y: Int,
        ): Boolean {
            val xParent = findSet(x)
            val yParent = findSet(y)

            if (xParent == yParent) {
                return false
            }

            when {
                ranks[xParent] < ranks[yParent] -> parents[xParent] = yParent
                ranks[xParent] > ranks[yParent] -> parents[yParent] = xParent
                else -> {
                    parents[xParent] = yParent
                    ranks[yParent] += 1
                }
            }
            return true
        }
    }
}
