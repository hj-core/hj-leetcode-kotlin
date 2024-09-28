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
        // Start with -root.score to neutralize the score of root
        dfs(root, -root.score) { pathScores, wordIndices ->
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

        // Scores of the prefix formed by chars from root to this
        var score = 0
            private set

        val children: List<Trie?>
            get() = _children
        private val _children = MutableList<Trie?>(26) { null }

        private fun add(
            word: String,
            from: Int,
            wordIndex: Int,
        ) {
            score += 1
            if (from == word.length) {
                addIndex(wordIndex)
                return
            }
            getOrPutChild(word[from]).add(word, from + 1, wordIndex)
        }

        private fun addIndex(wordIndex: Int) {
            if (_wordIndices == null) {
                _wordIndices = mutableListOf()
            }
            _wordIndices?.add(wordIndex)
        }

        private fun getOrPutChild(char: Char): Trie {
            val index = char - 'a'
            if (_children[index] == null) {
                _children[index] = Trie()
            }
            return checkNotNull(children[index])
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
        val newScores = pathScores + root.score
        onEachNode(newScores, root.wordIndices)

        for (child in root.children) {
            child?.let { dfs(it, newScores, onEachNode) }
        }
    }
}
