package com.hj.leetcode.kotlin.problem3479

/**
 * LeetCode page: [3479. Fruits Into Baskets III](https://leetcode.com/problems/fruits-into-baskets-iii/);
 */
class Solution2 {
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
    // The value of nodes, from root to leaves and level
    // by level. We place the root at index 1.
    private val values: IntArray,
) {
    // Places the fruit in the first large enough basket.
    // It returns false if such a basket does not exist.
    fun place(fruit: Int): Boolean {
        val (index, ok) = query(fruit)
        if (ok) {
            update(index, 0)
        }
        return ok
    }

    // Returns the index of the first large enough basket to
    // place the fruit. The boolean indicates whether such a
    // basket exists.
    private fun query(fruit: Int): Pair<Int, Boolean> {
        if (values[1] < fruit) {
            return -1 to false
        }

        var index = 1
        while (index < values.size) {
            if (values[index] < fruit) {
                index++
            }
            index *= 2
        }
        index /= 2
        return index to true
    }

    // Updates the value of the basket (a leaf).
    private fun update(
        index: Int,
        newValue: Int,
    ) {
        var k = index
        var kValue = newValue

        while (1 < k) {
            values[k] = kValue
            val sibIndex = k xor 1
            kValue = maxOf(kValue, values[sibIndex])
            k /= 2

            if (values[k] == kValue) {
                return
            }
        }
        values[k] = kValue
    }

    companion object {
        fun new(baskets: IntArray): MaxSegmentTree {
            var halfSize = 1
            while (halfSize < baskets.size) {
                halfSize *= 2
            }

            val values = IntArray(halfSize * 2)
            for (i in baskets.indices) {
                values[i + halfSize] = baskets[i]
            }

            var j = values.size - 1
            for (i in halfSize - 1 downTo 1) {
                values[i] = maxOf(values[j - 1], values[j])
                j -= 2
            }
            return MaxSegmentTree(values)
        }
    }
}
