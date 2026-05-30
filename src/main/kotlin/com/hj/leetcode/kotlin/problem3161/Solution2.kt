package com.hj.leetcode.kotlin.problem3161

import java.util.TreeSet

/**
 * LeetCode page: [3161. Block Placement Queries](https://leetcode.com/problems/block-placement-queries/);
 */
class Solution2 {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of queries.
    fun getResults(queries: Array<IntArray>): List<Boolean> {
        val effectiveSize = queries.indexOfLast { it[0] == 2 } + 1
        val allObstacles = collectUniqueObstacles(queries, 0..<effectiveSize).apply { sort() }
        val obstacleIndex = allObstacles.withIndex().associate { (index, point) -> point to index }
        val maxTree = MaxSegmentTree(allObstacles.size)
        val activeObstacles = TreeSet<Int>()

        val results = mutableListOf<Boolean>()
        for (i in 0..<effectiveSize) {
            val query = queries[i]
            when (query[0]) {
                1 -> { // install obstacle
                    val x = query[1]

                    val prev = activeObstacles.floor(x) ?: 0
                    if (prev == x) {
                        continue
                    }
                    maxTree.update(checkNotNull(obstacleIndex[x]), x - prev)

                    val next = activeObstacles.ceiling(x)
                    next?.let {
                        maxTree.update(checkNotNull(obstacleIndex[it]), it - x)
                    }

                    activeObstacles.add(x)
                }

                2 -> { // query placement
                    val (_, x, size) = query
                    val floor = activeObstacles.lower(x) ?: 0
                    val canPlace =
                        (x - floor >= size) ||
                            (obstacleIndex[floor]?.let { maxTree.query(it) >= size } ?: false)
                    results.add(canPlace)
                }
            }
        }

        return results
    }

    private fun collectUniqueObstacles(
        queries: Array<IntArray>,
        indexRange: IntRange,
    ): IntArray {
        val points = hashSetOf<Int>()
        for (i in indexRange) {
            if (queries[i][0] == 1) {
                points.add(queries[i][1])
            }
        }
        return points.toIntArray()
    }

    private class MaxSegmentTree(
        size: Int,
    ) {
        private val normalizedSize = computeNormalizeSize(size)
        private val container = IntArray(normalizedSize * 2)

        private fun computeNormalizeSize(contentSize: Int): Int =
            contentSize.takeHighestOneBit().let {
                if (it == contentSize) it else it * 2
            }

        private fun getLeftChild(parent: Int) = parent shl 1

        private fun getRightChild(parent: Int) = parent shl 1 xor 1

        fun update(
            index: Int,
            value: Int,
        ) {
            update(1, 0..<normalizedSize, index, value)
        }

        private fun update(
            parent: Int,
            parentCovered: IntRange,
            index: Int,
            value: Int,
        ) {
            if (parentCovered.first == parentCovered.last) {
                container[parent] = value
                return
            }

            val mid = parentCovered.first + (parentCovered.last - parentCovered.first) / 2
            val left = getLeftChild(parent)
            val right = getRightChild(parent)
            if (index <= mid) {
                update(left, parentCovered.first..mid, index, value)
            } else {
                update(right, mid + 1..parentCovered.last, index, value)
            }

            container[parent] = maxOf(container[left], container[right])
        }

        fun query(toIndex: Int): Int = query(1, 0..<normalizedSize, toIndex)

        private fun query(
            parent: Int,
            parentCovered: IntRange,
            // fromIndex: Int = 0,
            toIndex: Int,
        ): Int {
            if (parentCovered.first == parentCovered.last) {
                return container[parent]
            }

            val mid = parentCovered.first + (parentCovered.last - parentCovered.first) / 2
            val left = getLeftChild(parent)
            val right = getRightChild(parent)
            return if (toIndex <= mid) {
                query(left, parentCovered.first..mid, toIndex)
            } else {
                maxOf(
                    container[left],
                    query(right, mid + 1..parentCovered.last, toIndex),
                )
            }
        }
    }
}
