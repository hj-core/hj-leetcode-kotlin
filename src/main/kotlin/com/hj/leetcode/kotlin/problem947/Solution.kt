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
        var numConnectedComponents = 0
        val visited = BooleanArray(stones.size)
        val indicesByRow = stones.indices.groupBy { stones[it][0] }
        val indicesByColumn = stones.indices.groupBy { stones[it][1] }

        for (index in stones.indices) {
            if (visited[index]) continue

            numConnectedComponents++
            visited[index] = true
            val queue = ArrayDeque<Int>().apply { addLast(index) }

            while (queue.isNotEmpty()) {
                val first = queue.removeFirst()
                val stone = stones[first]
                val (row, column) = stone

                val sameRowIndices = indicesByRow[row] ?: emptyList()
                for (sameRowIndex in sameRowIndices) {
                    if (!visited[sameRowIndex]) {
                        queue.addLast(sameRowIndex)
                        visited[sameRowIndex] = true
                    }
                }

                val sameColumnIndices = indicesByColumn[column] ?: emptyList()
                for (sameColumnIndex in sameColumnIndices) {
                    if (!visited[sameColumnIndex]) {
                        queue.addLast(sameColumnIndex)
                        visited[sameColumnIndex] = true
                    }
                }
            }
        }

        return numConnectedComponents
    }
}