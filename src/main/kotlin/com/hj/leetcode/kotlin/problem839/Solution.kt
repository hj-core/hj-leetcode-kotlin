package com.hj.leetcode.kotlin.problem839

/**
 * LeetCode page: [839. Similar String Groups](https://leetcode.com/problems/similar-string-groups/);
 */
class Solution {
    /* Complexity:
     * Time O((N^2)*M) and Space O(N) where N is the size of strs and M is the length of words in strs;
     */
    fun numSimilarGroups(strs: Array<String>): Int {
        val unionFind = UnionFind(strs)
        return unionFind.numGroups()
    }

    private class UnionFind(strs: Array<String>) {
        private val parents = IntArray(strs.size) { it }
        private val ranks = IntArray(strs.size)

        init {
            update(strs)
        }

        private fun update(strs: Array<String>) {
            for (index in strs.indices) {
                for (otherIndex in index + 1 until strs.size) {
                    if (isSimilar(strs[index], strs[otherIndex])) {
                        union(index, otherIndex)
                    }
                }
            }
        }

        private fun isSimilar(string: String, other: String): Boolean {
            val isDifferentLength = string.length != other.length
            if (isDifferentLength) {
                return false
            }

            val differences = hashMapOf<Char, Char>()
            for (index in string.indices) {
                val char = string[index]
                val otherChar = other[index]

                if (char != otherChar) {
                    when (differences.size) {
                        0 -> differences[char] = otherChar
                        1 -> {
                            val cannotSwap = differences[otherChar] != char
                            if (cannotSwap) {
                                return false
                            } else {
                                differences[char] = otherChar
                            }
                        }

                        2 -> return false
                        else -> throw IllegalStateException()
                    }
                }
            }
            return true
        }

        private fun union(id: Int, otherId: Int) {
            val parent = find(id)
            val otherParent = find(otherId)
            if (parent == otherParent) {
                return
            }

            val rank = ranks[parent]
            val otherRank = ranks[otherParent]
            when {
                rank < otherRank -> parents[parent] = otherParent
                rank > otherRank -> parents[otherParent] = parent
                else -> {
                    parents[otherParent] = parent
                    ranks[parent]++
                }
            }
        }

        private tailrec fun find(id: Int): Int {
            return if (parents[id] == id) id else find(parents[id])
        }

        fun numGroups(): Int {
            val groups = hashSetOf<Int>()
            for (id in parents.indices) {
                groups.add(find(id))
            }
            return groups.size
        }
    }
}