package com.hj.leetcode.kotlin.problem336

/**
 * LeetCode page: [336. Palindrome Pairs](https://leetcode.com/problems/palindrome-pairs/);
 *
 * Note1 : There is an implementation of trieNode that also records the indices of all words whose subString start
 *    from there is a palindrome. It provides fast answer needed when finding pairs of unequal length, but requires
 *    checking of subString at each start index of each word is a palindrome. Not sure is a good deal or not,
 *    e.g. if all the words have similar length, this seems not a good option?
 *    See [O(n * k^2) java solution with Trie structure](https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure);
 *
 * Note2: There is an algorithm called Manacher's algorithm which seems can improve time complexity;
 */
class Solution {

    private class CustomTrieNode(val char: Char?) {
        init {
            require(char == null || char in 'a'..'z')
        }

        private val _nextNodes = MutableList<CustomTrieNode?>(26) { null }
        val nextNodes get() = _nextNodes as List<CustomTrieNode?>

        var wordIndex = -1
        val isTermination get() = wordIndex >= 0

        fun getNext(char: Char) = _nextNodes[char - 'a']

        fun setNext(node: CustomTrieNode): Boolean {
            val char = node.char ?: return false
            _nextNodes[char - 'a'] = node
            return true
        }
    }

    /* Complexity:
     * Time O(N * K^2) and Aux_Space O(M) where N is the size of words, K the longest length of word in words
     * and M is the flat length of words;
     */
    fun palindromePairs(words: Array<String>): List<List<Int>> {
        val trie = createTrie(words)
        val palindromePairs = mutableListOf<List<Int>>()
        for (index in words.indices) {
            val word = words[index]
            addPairsThatWordIsSuffix(word, index, trie, palindromePairs)
        }
        return palindromePairs
    }

    private fun createTrie(words: Array<String>): CustomTrieNode {
        val root = CustomTrieNode(null)
        for (index in words.indices) {
            val word = words[index]
            root.insert(word, index)
        }
        return root
    }

    private fun CustomTrieNode.insert(lowercaseOnly: String, wordIndex: Int) {
        var currNode = this

        for (char in lowercaseOnly) {
            val nodeNoExist = currNode.getNext(char) == null
            if (nodeNoExist) currNode.setNext(CustomTrieNode(char))

            currNode = checkNotNull(currNode.getNext(char))
        }
        currNode.wordIndex = wordIndex
    }

    private fun addPairsThatWordIsSuffix(
        word: String,
        wordIndex: Int,
        wordsTrie: CustomTrieNode,
        container: MutableList<List<Int>>
    ) {
        val lastMatchedNode =
            addPairsThatWordIsNotShorterAndReturnLastMatchedNode(word, wordIndex, wordsTrie, container)

        addPairsThatWordIsShorter(wordIndex, lastMatchedNode, container)
    }

    private fun addPairsThatWordIsNotShorterAndReturnLastMatchedNode(
        word: String,
        wordIndex: Int,
        wordsTrie: CustomTrieNode,
        container: MutableList<List<Int>>
    ): CustomTrieNode? {
        var currNode = wordsTrie
        val hasEmpty = currNode.wordIndex >= 0
        if (hasEmpty) addPairIfEmptyIsValidPrefix(word, wordIndex, currNode.wordIndex, container)

        for (index in word.indices.reversed()) {
            val char = word[index]
            val nodeNotExist = currNode.getNext(char) == null
            if (nodeNotExist) return null

            val nodeOfChar = checkNotNull(currNode.getNext(char))
            val isPalindromePairs = nodeOfChar.isTermination &&
                    nodeOfChar.wordIndex != wordIndex &&
                    word.isPalindrome(0 until index)
            if (isPalindromePairs) container.add(listOf(nodeOfChar.wordIndex, wordIndex))

            currNode = nodeOfChar
        }
        return currNode
    }

    private fun addPairIfEmptyIsValidPrefix(
        word: String,
        wordIndex: Int,
        indexOfEmpty: Int,
        container: MutableList<List<Int>>
    ) {
        val isPalindromePairs = indexOfEmpty != wordIndex && word.isPalindrome()
        if (isPalindromePairs) {
            container.add(listOf(indexOfEmpty, wordIndex))
        }
    }

    private fun CharSequence.isPalindrome(indexRange: IntRange = this.indices): Boolean {
        var leftIndex = indexRange.first
        var rightIndex = indexRange.last

        while (leftIndex < rightIndex) {
            if (this[leftIndex] != this[rightIndex]) return false
            leftIndex++
            rightIndex--
        }
        return true
    }

    private fun addPairsThatWordIsShorter(
        wordIndex: Int,
        lastMatchedNode: CustomTrieNode?,
        container: MutableList<List<Int>>,
        accString: StringBuilder = StringBuilder()
    ) {
        if (lastMatchedNode == null) return

        for (nodeOfChar in lastMatchedNode.nextNodes) {
            if (nodeOfChar != null) {
                accString.append(nodeOfChar.char)
                val isPalindromePairs = nodeOfChar.isTermination && accString.isPalindrome()
                if (isPalindromePairs) container.add(listOf(nodeOfChar.wordIndex, wordIndex))

                addPairsThatWordIsShorter(wordIndex, nodeOfChar, container, accString)
                accString.deleteCharAt(accString.lastIndex)
            }
        }
    }
}