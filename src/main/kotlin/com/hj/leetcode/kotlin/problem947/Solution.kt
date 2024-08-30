package com.hj.leetcode.kotlin.problem947

/**
 * LeetCode page: [947. Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of stones;
     */
    fun removeStones(stones: Array<IntArray>): Int = stones.size - numConnectedComponents(stones)

    private fun numConnectedComponents(stones: Array<IntArray>): Int {
        var result = 0
        val groupsByX = stones.indices.groupByTo(mutableMapOf()) { stones[it][0] }
        val groupsByY = stones.indices.groupByTo(mutableMapOf()) { stones[it][1] }

        for (index in stones.indices) {
            val (rootX, rootY) = stones[index]
            if (rootX !in groupsByX) {
                continue
            }

            result++
            val dfsStack = ArrayDeque<Int>()
            dfsStack.addAll(groupsByX[rootX] ?: emptyList())
            dfsStack.addAll(groupsByY[rootY] ?: emptyList())
            groupsByX.remove(rootX)
            groupsByY.remove(rootY)

            while (dfsStack.isNotEmpty()) {
                val popped = dfsStack.removeLast()
                val (x, y) = stones[popped]
                dfsStack.addAll(groupsByX[x] ?: emptyList())
                dfsStack.addAll(groupsByY[y] ?: emptyList())
                groupsByX.remove(x)
                groupsByY.remove(y)
            }
        }
        return result
    }
}
