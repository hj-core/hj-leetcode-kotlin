package com.hj.leetcode.kotlin.problem3691

import java.util.PriorityQueue

/**
 * LeetCode page: [3691. Maximum Total Subarray Value II](https://leetcode.com/problems/maximum-total-subarray-value-ii/);
 */
class Solution {
    // Complexity:
    // Time O(kLogN + NLogN) and Space O(N) where N is the length of nums.
    fun maxTotalValue(
        nums: IntArray,
        k: Int,
    ): Long {
        // Max pq of (fromIndex, toIndex, subarrayValue) by subarrayValue
        val pq = PriorityQueue(compareBy<IntArray> { -it[2] })

        // Initialize pq with each index pairing with the last index
        var rangeMin = nums.last()
        var rangeMax = rangeMin
        for (i in nums.indices.reversed()) {
            rangeMin = minOf(rangeMin, nums[i])
            rangeMax = maxOf(rangeMax, nums[i])
            val subarrayValue = rangeMax - rangeMin
            pq.add(intArrayOf(i, nums.lastIndex, subarrayValue))
        }

        // Poll the pq k times to determine the max total value
        val tree = MinMaxSegmentTree(nums)
        var totalValue = 0L
        repeat(k) {
            val (fromIndex, toIndex, subarrayValue) = pq.poll()
            totalValue += subarrayValue
            if (toIndex > fromIndex) {
                val (newMin, newMax) = tree.query(fromIndex..<toIndex)
                pq.offer(intArrayOf(fromIndex, toIndex - 1, newMax - newMin))
            }
        }

        return totalValue
    }

    private class MinMaxSegmentTree(
        input: IntArray,
    ) {
        private val size = computeSize(input.size)
        private val tree = LongArray(size)

        init {
            initTree(input)
        }

        private fun computeSize(inputSize: Int): Int {
            val msb = inputSize.takeHighestOneBit()
            return if (inputSize == msb) msb shl 1 else msb shl 4
        }

        private fun initTree(input: IntArray) {
            val halfSize = size / 2
            for ((i, value) in input.withIndex()) {
                setMinMax(halfSize + i, value, value)
            }

            if (halfSize > input.size) {
                tree.fill(
                    tree[halfSize + input.lastIndex],
                    halfSize + input.size,
                    size - 1,
                )
            }

            for (i in halfSize - 1 downTo 1) {
                val (leftMin, leftMax) = getMinMax(getLeft(i))
                val (rightMin, rightMax) = getMinMax(getRight(i))
                setMinMax(i, minOf(leftMin, rightMin), maxOf(leftMax, rightMax))
            }
        }

        private fun getLeft(node: Int): Int = node shl 1

        private fun getRight(node: Int): Int = node shl 1 xor 1

        private fun getMinMax(node: Int): Pair<Int, Int> {
            val min = (tree[node] and 0xFFFF_FFFF).toInt()
            val max = (tree[node] ushr 32).toInt()
            return Pair(min, max)
        }

        private fun setMinMax(
            node: Int,
            min: Int,
            max: Int,
        ) {
            tree[node] = max.toLong() shl 32 xor min.toLong()
        }

        fun query(indexRange: IntRange): Pair<Int, Int> = query(1, 0..<size / 2, indexRange)

        private fun query(
            node: Int,
            nodeRange: IntRange,
            queryRange: IntRange,
        ): Pair<Int, Int> {
            require(!(nodeRange.last < queryRange.first || queryRange.last < nodeRange.first))

            if (nodeRange == queryRange) {
                return getMinMax(node)
            }

            val nodeRangeMid = (nodeRange.first + nodeRange.last) ushr 1

            val left = getLeft(node)
            val leftRange = nodeRange.first..nodeRangeMid
            if (queryRange.last <= nodeRangeMid) {
                return query(left, leftRange, queryRange)
            }

            val right = getRight(node)
            val rightRange = nodeRangeMid + 1..nodeRange.last
            if (nodeRangeMid < queryRange.first) {
                return query(right, rightRange, queryRange)
            }

            val (leftMin, leftMax) = query(left, leftRange, queryRange.first..leftRange.last)
            val (rightMin, rightMax) = query(right, rightRange, rightRange.first..queryRange.last)
            return Pair(minOf(leftMin, rightMin), maxOf(leftMax, rightMax))
        }
    }
}
