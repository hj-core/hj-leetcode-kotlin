package com.hj.leetcode.kotlin.problem2416

/**
 * LeetCode page: [2416. Sum of Prefix Scores of Strings](https://leetcode.com/problems/sum-of-prefix-scores-of-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the flattened length of words.
     */
    fun sumPrefixScores(words: Array<String>): IntArray {
        val root = Trie.of(words)
        val result = IntArray(words.size)
        // Start with -root.prefixScores to neutralize the prefixScores of root
        dfs(root, -root.prefixScores) { pathScores, wordIndices ->
            for (index in wordIndices) {
                result[index] = pathScores
            }
        }
        return result
    }

    private class Trie private constructor() {
        val wordIndices: List<Int>
            get() = _wordIndices ?: emptyList()
        private var _wordIndices: MutableList<Int>? = null

        // Scores of the chars from root to this as a prefix
        var prefixScores = 0
            private set

        val children: List<Trie?>
            get() = _children
        private val _children = MutableList<Trie?>(26) { null }

        private fun add(
            word: String,
            from: Int,
            wordIndex: Int,
        ) {
            prefixScores += 1
            if (from == word.length) {
                if (_wordIndices == null) {
                    _wordIndices = mutableListOf()
                }
                _wordIndices?.add(wordIndex)
                return
            }
            val charIndex = word[from] - 'a'
            if (_children[charIndex] == null) {
                _children[charIndex] = Trie()
            }
            children[charIndex]?.add(word, from + 1, wordIndex)
        }

        companion object {
            fun of(words: Array<String>): Trie {
                val result = Trie()
                for ((i, word) in words.withIndex()) {
                    result.add(word, 0, i)
                }
                return result
            }
        }
    }

    private fun dfs(
        root: Trie,
        pathScores: Int,
        onEachNode: (pathScores: Int, wordIndices: List<Int>) -> Unit,
    ) {
        val newScores = pathScores + root.prefixScores
        if (root.wordIndices.isNotEmpty()) {
            onEachNode(newScores, root.wordIndices)
        }

        for (child in root.children) {
            child?.let { dfs(it, newScores, onEachNode) }
        }
    }
}
