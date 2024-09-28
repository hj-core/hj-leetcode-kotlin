package com.hj.leetcode.kotlin.problem2707

import kotlin.math.min

/**
 * LeetCode page: [2707. Extra Characters in a String](https://leetcode.com/problems/extra-characters-in-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2+M) and Space O(N+M) where N is the length of s
     * and M is the flattened length of dictionary.
     */
    fun minExtraChar(
        s: String,
        dictionary: Array<String>,
    ): Int = minExtraChar(s, 0, Trie.of(dictionary), mutableMapOf())

    private fun minExtraChar(
        s: String,
        from: Int,
        dictionary: Trie,
        memoization: MutableMap<Int, Int>, // memoization[from]::= minExtraChar(s[from:], dictionary)
    ): Int {
        if (from == s.length) {
            return 0
        }
        if (from in memoization) {
            return checkNotNull(memoization[from])
        }

        var result = s.length - from
        var trieNode: Trie? = dictionary
        // Try all possible lengths for the first split
        for (length in 1..(s.length - from)) {
            val firstEnd = from + length - 1
            trieNode = trieNode?.children?.get(s[firstEnd])
            val leftExtra = if (trieNode?.isTermination == true) 0 else length
            val rightExtra = minExtraChar(s, firstEnd + 1, dictionary, memoization)
            result = min(result, leftExtra + rightExtra)
        }
        memoization[from] = result
        return result
    }

    private class Trie private constructor() {
        var isTermination: Boolean = false
            private set

        val children
            get() = _children as Map<Char, Trie>
        private val _children = mutableMapOf<Char, Trie>()

        private fun add(
            word: String,
            from: Int,
        ) {
            if (from == word.length) {
                isTermination = true
                return
            }
            val child = _children.computeIfAbsent(word[from]) { Trie() }
            child.add(word, from + 1)
        }

        companion object {
            fun of(words: Array<String>): Trie {
                val result = Trie()
                for (word in words) {
                    result.add(word, 0)
                }
                return result
            }
        }
    }
}
