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
        val visited = BooleanArray(stones.size)
        val groupsByX = stones.indices.groupBy { stones[it][0] }
        val groupsByY = stones.indices.groupBy { stones[it][1] }

        for (index in stones.indices) {
            if (visited[index]) {
                continue
            }

            result++
            val dfsStack = ArrayDeque<Int>()
            dfsStack.addLast(index)
            visited[index] = true

            while (dfsStack.isNotEmpty()) {
                val popped = dfsStack.removeLast()
                val (x, y) = stones[popped]
                val nextIndices =
                    listOf(
                        checkNotNull(groupsByX[x]),
                        checkNotNull(groupsByY[y]),
                    ).asSequence().flatten()

                for (next in nextIndices) {
                    if (!visited[next]) {
                        dfsStack.addLast(next)
                        visited[next] = true
                    }
                }
            }
        }
        return result
    }
}
