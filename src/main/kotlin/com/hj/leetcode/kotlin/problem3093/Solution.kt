package com.hj.leetcode.kotlin.problem3093

/**
 * LeetCode page: [3093. Longest Common Suffix Queries](https://leetcode.com/problems/longest-common-suffix-queries/);
 */
class Solution {
    // Complexity:
    // Time O(L1+L2) and Space O(L1) where L1 is the flattened length of
    // wordsContainer and L2 is the flattened length of wordsQuery.
    fun stringIndices(
        wordsContainer: Array<String>,
        wordsQuery: Array<String>,
    ): IntArray {
        val root = TrieNode.build(wordsContainer)
        return IntArray(wordsQuery.size) { root.query(wordsQuery[it]) }
    }

    private class TrieNode {
        var ansIndex: Int = -1
        var ansLength: Int = 0
        val children = mutableMapOf<Char, TrieNode>()

        fun query(query: String): Int {
            var node = this
            for (i in query.indices.reversed()) {
                node = node.children[query[i]] ?: break
            }
            return node.ansIndex
        }

        companion object {
            fun build(wordsContainer: Array<String>): TrieNode =
                TrieNode().apply {
                    addAll(wordsContainer)
                    solveLocalAnswer()
                }

            private fun TrieNode.addAll(wordsContainer: Array<String>) {
                for ((index, word) in wordsContainer.withIndex()) {
                    add(index, word)
                }
            }

            private fun TrieNode.add(
                index: Int,
                word: String,
            ) {
                var next = this
                for (i in word.indices.reversed()) {
                    next = next.children.computeIfAbsent(word[i]) { TrieNode() }
                }

                if (next.ansIndex == -1) {
                    next.ansIndex = index
                    next.ansLength = word.length
                } else {
                    next.ansIndex = minOf(index, next.ansIndex)
                }
            }

            private fun TrieNode.solveLocalAnswer(): Int {
                for (child in children.values) {
                    child.solveLocalAnswer()
                }

                if (ansIndex == -1 && children.isNotEmpty()) {
                    val node =
                        children.values.minWith(
                            compareBy(TrieNode::ansLength, TrieNode::ansIndex),
                        )
                    ansIndex = node.ansIndex
                    ansLength = node.ansLength
                }

                return ansIndex
            }
        }
    }
}
