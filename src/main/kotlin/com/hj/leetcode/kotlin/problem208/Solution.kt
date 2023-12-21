package com.hj.leetcode.kotlin.problem208

/**
 * LeetCode page: [208. Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/);
 */
class Trie() {

    private var isEndOfWord = false
    private val children = hashMapOf<Char, Trie>()

    /* Complexity:
     * Time O(L) and Space O(L) where L is the length of word;
     */
    fun insert(word: String) {
        var currNode = this
        for (char in word) {
            currNode = currNode.children.computeIfAbsent(char) { Trie() }
        }
        currNode.setEndOfWord()
    }

    private fun setEndOfWord() {
        isEndOfWord = true
    }

    private fun isEndOfWord(): Boolean {
        return isEndOfWord
    }

    /* Complexity:
     * Time O(L) and Space O(1) where L is the length of word;
     */
    fun search(word: String): Boolean {
        return findNodeOfLastChar(word)?.isEndOfWord() ?: false
    }

    private fun findNodeOfLastChar(word: String): Trie? {
        var currNode = this
        for (char in word) {
            val nextNode = currNode.children[char] ?: return null
            currNode = nextNode
        }
        return currNode
    }

    /* Complexity:
     * Time O(L) and Space O(1) where L is the length of prefix;
     */
    fun startsWith(prefix: String): Boolean {
        return findNodeOfLastChar(prefix) != null
    }
}