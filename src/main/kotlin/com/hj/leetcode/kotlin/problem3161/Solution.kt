package com.hj.leetcode.kotlin.problem3161

import java.util.TreeSet

/**
 * LeetCode page: [3161. Block Placement Queries](https://leetcode.com/problems/block-placement-queries/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of queries.
    fun getResults(queries: Array<IntArray>): List<Boolean> {
        val effectiveSize = queries.indexOfLast { it[0] == 2 } + 1
        val allPoints = collectUniquePoints(queries, 0..<effectiveSize).apply { sort() }
        val pointIndex = allPoints.withIndex().associate { (index, point) -> point to index }
        val activeObstacles = TreeSet<Int>()
        val maxTree = MaxSegmentTree(allPoints)

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
                    val next = activeObstacles.ceiling(x) ?: allPoints.last()
                    val fromIndex = 1 + checkNotNull(pointIndex[x])
                    val toIndex = checkNotNull(pointIndex[next])
                    val delta = prev - x
                    maxTree.update(fromIndex..toIndex, delta)
                    activeObstacles.add(x)
                }

                2 -> { // query placement
                    val (_, x, size) = query
                    val toIndex = checkNotNull(pointIndex[x])
                    val maxSpace = maxTree.query(toIndex)
                    results.add(size <= maxSpace)
                }
            }
        }

        return results
    }

    private fun collectUniquePoints(
        queries: Array<IntArray>,
        indexRange: IntRange,
    ): IntArray {
        val points = hashSetOf<Int>()
        for (i in indexRange) {
            points.add(queries[i][1])
        }
        return points.toIntArray()
    }

    private class MaxSegmentTree(
        initialValues: IntArray,
    ) {
        private val normalizedSize = computeNormalizeSize(initialValues.size)

        // Each entry of container is (delta shl 32 xor base).
        private val container = LongArray(normalizedSize * 2)

        init {
            initialize(initialValues)
        }

        private fun computeNormalizeSize(contentSize: Int): Int =
            contentSize.takeHighestOneBit().let {
                if (it == contentSize) it else it * 2
            }

        private fun initialize(initialValues: IntArray) {
            for (index in normalizedSize until normalizedSize + initialValues.size) {
                container[index] = initialValues[index - normalizedSize].toLong()
            }
            for (index in normalizedSize - 1 downTo 1) {
                val left = getLeftChild(index)
                val right = getRightChild(index)
                container[index] = maxOf(container[left], container[right])
            }
        }

        private fun getLeftChild(parent: Int) = parent shl 1

        private fun getRightChild(parent: Int) = parent shl 1 xor 1

        fun update(
            indexRange: IntRange,
            delta: Int,
        ) {
            update(1, 0..<normalizedSize, indexRange, delta.toLong())
        }

        private fun update(
            parent: Int,
            parentCovered: IntRange,
            indexRange: IntRange,
            delta: Long,
        ) {
            if (indexRange.isEmpty()) {
                return
            }
            if (parentCovered == indexRange) {
                addDelta(parent, delta)
                return
            }

            val mid = parentCovered.first + (parentCovered.last - parentCovered.first) / 2
            val left = getLeftChild(parent)
            val leftCovered = parentCovered.first..mid
            val right = getRightChild(parent)
            val rightCovered = mid + 1..parentCovered.last

            when {
                indexRange.last <= mid -> {
                    update(left, leftCovered, indexRange, delta)
                }

                mid < indexRange.first -> {
                    update(right, rightCovered, indexRange, delta)
                }

                else -> {
                    update(left, leftCovered, indexRange.first..mid, delta)
                    update(right, rightCovered, mid + 1..indexRange.last, delta)
                }
            }

            val newBase = maxOf(computeValue(left), computeValue(right))
            val (delta, _) = unpack(container[parent])
            container[parent] = pack(newBase, delta)
        }

        private fun computeValue(node: Int): Long {
            val (delta, base) = unpack(container[node])
            return base + delta
        }

        private fun addDelta(
            node: Int,
            delta: Long,
        ) {
            container[node] += delta shl 32
        }

        private fun pack(
            base: Long,
            delta: Long,
        ) = delta shl 32 xor base

        private fun unpack(entry: Long): Pair<Long, Long> =
            Pair(entry shr 32, entry and 0xFFFF_FFFF)

        fun query(toIndex: Int): Int = query(1, 0..<normalizedSize, toIndex, 0).toInt()

        private fun query(
            parent: Int,
            parentCovered: IntRange,
            // fromIndex: Int = 0,
            toIndex: Int,
            accDelta: Long,
        ): Long {
            val (delta, base) = unpack(container[parent])
            val accDelta = accDelta + delta
            if (parentCovered.last == toIndex) {
                return base + accDelta
            }

            val mid = parentCovered.first + (parentCovered.last - parentCovered.first) / 2
            val left = getLeftChild(parent)
            val leftCovered = parentCovered.first..mid
            val right = getRightChild(parent)
            val rightCovered = mid + 1..parentCovered.last

            return if (toIndex <= mid) {
                query(left, leftCovered, toIndex, accDelta)
            } else {
                maxOf(
                    computeValue(left) + accDelta,
                    query(right, rightCovered, toIndex, accDelta),
                )
            }
        }
    }
}
