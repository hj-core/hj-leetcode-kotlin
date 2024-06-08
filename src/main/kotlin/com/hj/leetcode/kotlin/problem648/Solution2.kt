package com.hj.leetcode.kotlin.problem648

/**
 * LeetCode page: [648. Replace Words](https://leetcode.com/problems/replace-words/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N+L) and Space O(N+L) where N is the flattened length of dictionary
     * and L is the length of sentence;
     */
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        val rootTrie = Trie(false).apply {
            insertAll(dictionary)
        }
        return sentence
            .splitToSequence(" ")
            .map { word -> searchRootOrSelf(word, rootTrie) }
            .joinToString(" ")
    }

    private class Trie(isEndOfWord: Boolean) {
        var isEndOfWord = isEndOfWord
            private set

        private val _nextNodes = mutableMapOf<Char, Trie>()
        val nextNodes: Map<Char, Trie> get() = _nextNodes

        fun insertAll(words: List<String>) {
            for (word in words) {
                insert(word)
            }
        }

        fun insert(word: String) {
            insert(word, 0)
        }

        private fun insert(word: String, fromIndex: Int) {
            if (fromIndex == word.length) {
                isEndOfWord = true
                return
            }

            _nextNodes
                .computeIfAbsent(word[fromIndex]) { _ -> Trie(false) }
                .insert(word, fromIndex + 1)
        }
    }

    private fun searchRootOrSelf(word: String, rootTrie: Trie): String {
        var currentNode = rootTrie
        for ((index, char) in word.withIndex()) {
            if (char !in currentNode.nextNodes) {
                return word
            }

            currentNode = checkNotNull(currentNode.nextNodes[char])
            if (currentNode.isEndOfWord) {
                return word.substring(0..index)
            }
        }
        return word
    }
}