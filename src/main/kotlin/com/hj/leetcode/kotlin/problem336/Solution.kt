package com.hj.leetcode.kotlin.problem336

/**
 * LeetCode page: [336. Palindrome Pairs](https://leetcode.com/problems/palindrome-pairs/);
 *
 * TODO 336-1 : Determine the time complexity;
 * TODO 336-2 : Improve readability;
 */
class Solution {
    /* Complexity:
     * Time O(??) and Aux_Space O(M) where M is the flat length of words;
     */
    fun palindromePairs(words: Array<String>): List<List<Int>> {
        val pairs = mutableListOf<List<Int>>()
        addPalindromePairsOfWords(pairs, words)
        return pairs
    }

    private fun addPalindromePairsOfWords(
        container: MutableList<List<Int>>,
        words: Array<String>
    ) {
        addPalindromePairsThatHasEmptyString(container, words)
        addPalindromePairsWithNonEmptyStrings(container, words)
    }

    private fun addPalindromePairsThatHasEmptyString(
        container: MutableList<List<Int>>,
        words: Array<String>
    ) {
        val indexOfEmptyString = words.indexOf("")
        val noEmptyString = indexOfEmptyString == -1
        if (noEmptyString) return

        for ((index, word) in words.withIndex()) {
            if (word.isPalindrome()) {
                container.add(listOf(index, indexOfEmptyString))
                container.add(listOf(indexOfEmptyString, index))
            }
        }
    }

    private fun CharSequence.isPalindrome(indexRange: IntRange = this.indices): Boolean {
        if (this.isEmpty()) return false
        var leftIndex = indexRange.first
        var rightIndex = indexRange.last

        while (leftIndex < rightIndex) {
            if (this[leftIndex] != this[rightIndex]) return false
            leftIndex++
            rightIndex--
        }
        return true
    }

    private fun addPalindromePairsWithNonEmptyStrings(
        container: MutableList<List<Int>>,
        words: Array<String>
    ) {
        val trie = createTrieExcludingEmptyString(words)
        for ((index, word) in words.withIndex()) {
            if (word.isEmpty()) continue
            addPalindromePairsOfWord(container, word, index, trie.root)
        }
    }

    private fun createTrieExcludingEmptyString(words: Array<String>): CustomTrie {
        val trie = CustomTrie()
        for ((index, word) in words.withIndex()) {
            if (word.isNotEmpty()) trie.insert(word, index)
        }
        return trie
    }

    private class CustomTrie {
        val root = CustomTrieNode(null)

        fun insert(lowercaseOnly: String, wordIndex: Int) {
            var currNode = root
            for (char in lowercaseOnly) {
                val childIndex = currNode.getChildIndex(char)
                val childNotExist = currNode.children[childIndex] == null
                if (childNotExist) {
                    currNode.children[childIndex] = CustomTrieNode(char)
                }
                currNode = checkNotNull(currNode.children[childIndex])
            }
            currNode.wordIndex = wordIndex
        }
    }

    private class CustomTrieNode(val char: Char?) {
        init {
            require(char == null || char in 'a'..'z')
        }

        val children = MutableList<CustomTrieNode?>(26) { null }
        var wordIndex = -1
        val isTermination get() = wordIndex >= 0

        fun getChildIndex(char: Char) = char - 'a'
    }

    private fun addPalindromePairsOfWord(
        container: MutableList<List<Int>>,
        word: String,
        wordIndex: Int,
        trieRoot: CustomTrieNode
    ) {
        val lastMatchedNode =
            addPalindromePairThatCounterPartIsNotLongerAndReturnLastMatchedNode(container, word, wordIndex, trieRoot)

        if (lastMatchedNode != null) {
            addPalindromePairThatCounterPartIsLonger(container, wordIndex, lastMatchedNode)
        }
    }

    private fun addPalindromePairThatCounterPartIsNotLongerAndReturnLastMatchedNode(
        container: MutableList<List<Int>>,
        word: String,
        wordIndex: Int,
        trieRoot: CustomTrieNode
    ): CustomTrieNode? {
        var currNode = trieRoot
        for (charIndex in word.indices.reversed()) {
            val char = word[charIndex]
            val childIndex = currNode.getChildIndex(char)
            val matchedChild = currNode.children[childIndex]

            val noMatchedChild = matchedChild == null
            if (noMatchedChild) return null

            checkNotNull(matchedChild)
            val canFormPalindromePairs = matchedChild.isTermination &&
                    matchedChild.wordIndex != wordIndex &&
                    word.isPalindrome(0 until charIndex)
            if (canFormPalindromePairs) {
                container.add(listOf(matchedChild.wordIndex, wordIndex))
            }

            currNode = matchedChild
        }
        return currNode
    }

    private fun addPalindromePairThatCounterPartIsLonger(
        container: MutableList<List<Int>>,
        wordIndex: Int,
        lastMatchedNode: CustomTrieNode,
        accString: StringBuilder = StringBuilder()
    ) {
        for (child in lastMatchedNode.children) {
            if (child != null) {
                accString.append(child.char)
                if (child.isTermination && accString.isPalindrome()) {
                    container.add(listOf(child.wordIndex, wordIndex))
                }
                addPalindromePairThatCounterPartIsLonger(container, wordIndex, child, accString)
                accString.deleteCharAt(accString.lastIndex)
            }
        }
    }
}