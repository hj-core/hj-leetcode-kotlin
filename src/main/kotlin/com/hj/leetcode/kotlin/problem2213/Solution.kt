package com.hj.leetcode.kotlin.problem2213

import java.util.TreeSet

/**
 * LeetCode page: [2213. Longest Substring of One Repeating Character](https://leetcode.com/problems/longest-substring-of-one-repeating-character/);
 */
class Solution {
    // Complexity:
    // Time O((N+M)LogN) and Auxiliary Space O(N) where N is the length of s
    // and M is the number of queries.
    fun longestRepeating(
        s: String,
        queryCharacters: String,
        queryIndices: IntArray,
    ): IntArray {
        val s = s.toCharArray()

        val tree = MaxSegmentTree(s.size)
        val breaks = TreeSet<Int>() // where s[i] != s[i-1]

        var lastBreak = 0
        for (sIdx in 1..<s.size) {
            if (s[sIdx] != s[sIdx - 1]) {
                breaks.add(lastBreak)
                tree.update(lastBreak, sIdx - lastBreak)
                lastBreak = sIdx
            }
        }
        breaks.add(lastBreak)
        tree.update(lastBreak, s.size - lastBreak)

        val answers = IntArray(queryIndices.size)
        for (i in answers.indices) {
            val sIdx = queryIndices[i]
            val newChar = queryCharacters[i]

            if (s[sIdx] == newChar) {
                answers[i] = tree.max()
                continue
            }

            val left = breaks.lower(sIdx) ?: -1
            val right = breaks.higher(sIdx) ?: s.size

            // Update Left
            val leftLen =
                when {
                    left == -1 -> 0
                    s[left] != newChar -> sIdx - left
                    s.getOrNull(sIdx + 1) != newChar -> sIdx + 1 - left
                    else -> right - left + tree.queryLen(right)
                }
            tree.update(left, leftLen)

            // Update sIdx
            val sIdxLen =
                when {
                    s.getOrNull(left) == newChar -> 0
                    s.getOrNull(sIdx + 1) != newChar -> 1
                    else -> 1 + tree.queryLen(right)
                }
            tree.update(sIdx, sIdxLen)
            if (sIdxLen > 0) {
                breaks.add(sIdx)
            } else {
                breaks.remove(sIdx)
            }

            // Update sIdx + 1
            if (right != sIdx + 1) {
                breaks.add(sIdx + 1)
                tree.update(sIdx + 1, right - sIdx - 1)
            }

            // Update right
            if (right == sIdx + 1 && s.getOrNull(right) == newChar) {
                breaks.remove(right)
                tree.update(right, 0)
            }

            s[sIdx] = newChar
            answers[i] = tree.max()
        }

        return answers
    }

    private class MaxSegmentTree(
        private val inputSize: Int,
    ) {
        private val size = computeTreeSize(inputSize)
        private val tree = IntArray(size)

        private fun computeTreeSize(inputSize: Int): Int =
            inputSize
                .takeHighestOneBit()
                .let { if (it == inputSize) it * 2 else it * 4 }
                .coerceAtLeast(4)

        fun update(
            inputIdx: Int,
            value: Int,
        ) {
            if (inputIdx in 0..<inputSize) {
                updateTree(size / 2 + inputIdx, value)
            }
        }

        private tailrec fun updateTree(
            treeIdx: Int,
            value: Int,
        ) {
            if (treeIdx == 1) {
                tree[treeIdx] = value
                return
            }
            if (tree[treeIdx] == value) {
                return
            }

            tree[treeIdx] = value
            updateTree(parent(treeIdx), maxOf(value, tree[sibling(treeIdx)]))
        }

        private fun parent(treeIdx: Int): Int = if (treeIdx == 1) 1 else treeIdx shr 1

        private fun sibling(treeIdx: Int): Int = treeIdx xor 1

        fun queryLen(inputIdx: Int): Int =
            if (inputIdx in 0..<inputSize) tree[size / 2 + inputIdx] else 0

        fun max(): Int = tree[1]
    }
}
