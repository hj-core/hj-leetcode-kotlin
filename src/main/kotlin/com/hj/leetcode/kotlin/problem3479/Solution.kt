package com.hj.leetcode.kotlin.problem3479

/**
 * LeetCode page: [3479. Fruits Into Baskets III](https://leetcode.com/problems/fruits-into-baskets-iii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of
    // fruits and baskets.
    fun numOfUnplacedFruits(
        fruits: IntArray,
        baskets: IntArray,
    ): Int {
        val tree = MaxSegmentTree.new(baskets)
        return fruits.count { !tree.place(it) }
    }
}

private class MaxSegmentTree(
    // The start indices for each level, from leaves to root.
    private val starts: IntArray,
    // The value of nodes, from leaves to root and level by
    // level.
    private val values: IntArray,
) {
    // Places the fruit in the first large enough basket.
    // It returns false if such a basket does not exist.
    fun place(fruit: Int): Boolean {
        val (path, ok) = query(fruit)
        if (ok) {
            update(path, 0)
        }
        return ok
    }

    // Returns the path (indices) from the first large
    // enough basket to the root. The boolean indicates
    // whether such a basket exists.
    private fun query(fruit: Int): Pair<IntArray, Boolean> {
        var index = values.lastIndex
        if (values[index] < fruit) {
            return Pair(intArrayOf(), false)
        }

        // Walk from the root to the leaf
        val path = IntArray(starts.size)
        for (i in starts.lastIndex downTo 1) {
            path[i] = index
            val currStart = starts[i]
            val nextStart = starts[i - 1]

            index = nextStart + (index - currStart) * 2
            if (values[index] < fruit) {
                index++
            }
        }
        path[0] = index
        return Pair(path, true)
    }

    // Updates the leafValue given a path from that leaf to
    // the root.
    private fun update(
        path: IntArray,
        leafValue: Int,
    ) {
        // Walk from the leaf to the root
        var value = leafValue
        for (i in 0..<(starts.size - 1)) {
            val index = path[i]
            values[index] = value

            val isLeft = (starts[i] and 1) == (index and 1)
            val sibIndex = if (isLeft) index + 1 else index - 1
            if (sibIndex < starts[i + 1]) {
                value = maxOf(value, values[sibIndex])
            }

            if (value == values[path[i + 1]]) {
                return
            }
        }
        values[values.lastIndex] = value
    }

    companion object {
        fun new(baskets: IntArray): MaxSegmentTree {
            // Determine the start indices for each level
            var start = 0
            var width = baskets.size
            val levelStarts = mutableListOf<Int>()
            while (width > 1) {
                levelStarts.add(start)
                start += width
                width = (width + 1) / 2
            }
            levelStarts.add(start)

            // Fill values from the leaves to the root
            val values = IntArray(start + 1)

            for (i in baskets.indices) {
                values[i] = baskets[i]
            }
            for (i in 1..<levelStarts.size) {
                val prevStart = levelStarts[i - 1]
                val currStart = levelStarts[i]
                var k = currStart

                for (j in (prevStart + 1)..<currStart step 2) {
                    values[k] = maxOf(values[j - 1], values[j])
                    k++
                }
                if ((currStart - prevStart) and 1 == 1) {
                    values[k] = values[currStart - 1]
                }
            }

            return MaxSegmentTree(levelStarts.toIntArray(), values)
        }
    }
}
