package com.hj.leetcode.kotlin.problem211

/**
 * LeetCode page: [211. Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/);
 */
class WordDictionary() {

    private val root = Trie(wildcards = '.')

    fun addWord(word: String) {
        root.addWord(word)
    }

    fun search(word: String): Boolean {
        return root.search(word)
    }

    private class Trie(private val wildcards: Char) {

        private var isEndOfWord = false
        private val children = hashMapOf<Char, Trie>()

        /* Complexity:
         * Time O(L) and Space O(L) where L is the length of word;
         */
        fun addWord(word: String) {
            var currNode = this
            for (char in word) {
                currNode = currNode.children.computeIfAbsent(char) { Trie(wildcards) }
            }
            currNode.setEndOfWord()
        }

        private fun setEndOfWord() {
            isEndOfWord = true
        }

        private fun isEndOfWord() = isEndOfWord

        /* Complexity:
         * Time O(A^N * L) and Space O(L) where A and N are the number of possible char and the
         * maximum number of wildcards in word, L is the length of word;
         */
        fun search(word: String): Boolean {
            return search(word, 0)
        }

        private fun search(word: String, fromIndex: Int): Boolean {
            if (fromIndex == word.length) {
                return this.isEndOfWord()
            }

            val currChar = word[fromIndex]

            return if (currChar == wildcards) {
                children.values.any { it.search(word, fromIndex + 1) }
            } else {
                children[currChar]?.search(word, fromIndex + 1) ?: false
            }
        }
    }
}