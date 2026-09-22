package com.hj.leetcode.kotlin.problem3525

/**
 * LeetCode page: [3525. Find X Value of Array II](https://leetcode.com/problems/find-x-value-of-array-ii/);
 */
class Solution {
    // Complexity:
    // Time O(kN + kMLogN) and Space O(kN) where N is the length of nums
    // and M is the length of queries.
    fun resultArray(
        nums: IntArray,
        k: Int,
        queries: Array<IntArray>,
    ): IntArray {
        val tree = SegmentTree(nums, k)

        return IntArray(queries.size) {
            val (i, value, start, x) = queries[it]
            tree.update(i, value)
            tree.query(start, x)
        }
    }

    private class SegmentTree(
        nums: IntArray,
        private val k: Int,
    ) {
        private val tree = build(nums)

        private fun build(nums: IntArray): Array<IntArray> {
            val size =
                nums.size
                    .takeHighestOneBit()
                    .let { if (it == nums.size) it * 2 else it * 4 }
            val tree = Array(size) { IntArray(k + 1).apply { this[k] = 1 } }

            // Initialize leaves
            val halfSize = size / 2
            for ((index, num) in nums.withIndex()) {
                val modK = num % k
                val leaf = tree[index + halfSize]
                leaf[k] = modK
                leaf[modK] = 1
            }

            // Initialize internal nodes
            for (right in size - 1 downTo 3 step 2) {
                val left = right - 1
                val parent = left shr 1
                merge(tree[left], tree[right]).copyInto(tree[parent])
            }

            return tree
        }

        private fun merge(
            left: IntArray,
            right: IntArray,
        ): IntArray {
            val merged = left.clone()
            merged[k] = (merged[k] * right[k]) % k
            for (modK in 0..<k) {
                merged[left[k] * modK % k] += right[modK]
            }
            return merged
        }

        fun update(
            index: Int,
            value: Int,
        ) {
            var treeIndex = tree.size / 2 + index

            // Update leaf
            val modK = value % k
            tree[treeIndex].fill(0)
            tree[treeIndex][k] = modK
            tree[treeIndex][modK] = 1

            // Update internal nodes
            while (1 < treeIndex) {
                val right = treeIndex or 1
                val left = right xor 1
                val parent = left shr 1
                merge(tree[left], tree[right]).copyInto(tree[parent])
                treeIndex = parent
            }
        }

        fun query(
            start: Int,
            x: Int,
        ): Int = queryRange(1, 0, tree.size / 2, start, tree.size / 2)[x]

        private fun queryRange(
            parent: Int,
            parentStart: Int,
            parentEnd: Int,
            queryStart: Int,
            queryEnd: Int,
        ): IntArray {
            if (queryStart == parentStart && queryEnd == parentEnd) {
                return tree[parent].clone()
            }

            val mid = (parentStart + parentEnd) ushr 1
            if (queryEnd <= mid) {
                return queryRange(parent * 2, parentStart, mid, queryStart, queryEnd)
            }
            if (mid <= queryStart) {
                return queryRange(parent * 2 + 1, mid, parentEnd, queryStart, queryEnd)
            }

            val left = queryRange(parent * 2, parentStart, mid, queryStart, mid)
            val right = queryRange(parent * 2 + 1, mid, parentEnd, mid, queryEnd)
            return merge(left, right)
        }
    }
}
