package com.hj.leetcode.kotlin.problem947

/**
 * LeetCode page: [947. Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of stones;
     */
    fun removeStones(stones: Array<IntArray>): Int {
        return stones.size - numConnectedComponents(stones)
    }

    private fun numConnectedComponents(stones: Array<IntArray>): Int {
        var result = 0
        val visited = BooleanArray(stones.size)
        val indicesByRow = stones.indices.groupBy { stones[it][0] }
        val indicesByColumn = stones.indices.groupBy { stones[it][1] }

        for (index in stones.indices) {
            if (visited[index]) {
                continue
            }

            result++
            val dfsStack = ArrayDeque<Int>()
            dfsStack.addLast(index)
            visited[index] = true

            while (dfsStack.isNotEmpty()) {
                val poppedIndex = dfsStack.removeLast()
                val (row, column) = stones[poppedIndex]
                val nextIndices = listOf(
                    checkNotNull(indicesByRow[row]),
                    checkNotNull(indicesByColumn[column])
                ).asSequence().flatten()

                for (nextIndex in nextIndices) {
                    if (!visited[nextIndex]) {
                        dfsStack.addLast(nextIndex)
                        visited[nextIndex] = true
                    }
                }
            }
        }
        return result
    }
}