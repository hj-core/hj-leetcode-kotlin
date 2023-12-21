package com.hj.leetcode.kotlin.problem947

/**
 * LeetCode page: [947. Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of stones;
     */
    fun removeStones(stones: Array<IntArray>): Int {
        val rowGroups = stones.indices.groupBy { stones[it][0] }
        val columnGroups = stones.indices.groupBy { stones[it][1] }
        val numConnectedComponents = findNumConnectedComponents(stones, rowGroups, columnGroups)
        return stones.size - numConnectedComponents
    }

    private fun findNumConnectedComponents(
        stones: Array<IntArray>,
        rowGroups: Map<Int, List<Int>>,
        columnGroups: Map<Int, List<Int>>
    ): Int {
        var numConnectedComponents = 0
        val visited = BooleanArray(stones.size)

        for (index in stones.indices) {
            if (visited[index]) continue

            numConnectedComponents++
            visited[index] = true
            val queue = ArrayDeque<Int>().apply { addLast(index) }

            while (queue.isNotEmpty()) {
                val first = queue.removeFirst()
                val stone = stones[first]
                val (row, column) = stone

                val sameRowIndices = rowGroups[row] ?: emptyList()
                for (sameRowIndex in sameRowIndices) {
                    if (!visited[sameRowIndex]) {
                        queue.addLast(sameRowIndex)
                        visited[sameRowIndex] = true
                    }
                }

                val sameColumnIndices = columnGroups[column] ?: emptyList()
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