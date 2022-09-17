package com.hj.leetcode.kotlin.problem336

/**
 * LeetCode page: [336. Palindrome Pairs](https://leetcode.com/problems/palindrome-pairs/);
 *
 * TODO : There is an implementation of trieNode that also records the indices of all words whose subString start
 *    from there is a palindrome. It provides fast answer needed when finding pairs of unequal length, but requires
 *    checking of subString at each start index of each word is a palindrome. Not sure is a good deal or not,
 *    e.g. if all the words have similar length, this seems not a good option?
 *    See [O(n * k^2) java solution with Trie structure](https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure);
 *
 */
class Solution {
    /* Complexity:
     * Time O(N * K^2) and Aux_Space O(M) where N is the size of words, K the longest length of word in words
     * and M is the flat length of words;
     */
    fun palindromePairs(words: Array<String>): List<List<Int>> {
        val pairs = mutableListOf<List<Int>>()
        addPalindromePairsInWords(pairs, words)
        return pairs
    }

    private fun addPalindromePairsInWords(
        container: MutableList<List<Int>>,
        words: Array<String>
    ) {
        addPairsThatHasEmptyString(container, words)
        addPairsWithoutEmptyString(container, words)
    }

    private fun addPairsThatHasEmptyString(
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

    private fun addPairsWithoutEmptyString(
        container: MutableList<List<Int>>,
        words: Array<String>
    ) {
        val trie = createTrieWithEmptyStringExcluded(words)
        for ((index, word) in words.withIndex()) {
            if (word.isNotEmpty()) addPairsThatWordIsSuffix(container, word, index, trie.root)
        }
    }

    private fun createTrieWithEmptyStringExcluded(words: Array<String>): CustomTrie {
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
                val nodeNoExist = currNode.getNext(char) == null
                if (nodeNoExist) currNode.setNext(CustomTrieNode(char))

                currNode = checkNotNull(currNode.getNext(char))
            }
            currNode.wordIndex = wordIndex
        }
    }

    private class CustomTrieNode(val char: Char?) {
        init {
            require(char == null || char in 'a'..'z')
        }

        val nextNodes = MutableList<CustomTrieNode?>(26) { null }
        var wordIndex = -1
        val isTermination get() = wordIndex >= 0

        fun getNext(char: Char) = nextNodes[char - 'a']

        fun setNext(node: CustomTrieNode): Boolean {
            val char = node.char ?: return false
            nextNodes[char - 'a'] = node
            return true
        }
    }

    private fun addPairsThatWordIsSuffix(
        container: MutableList<List<Int>>,
        word: String,
        wordIndex: Int,
        trieRoot: CustomTrieNode
    ) {
        var currNode = trieRoot
        for (index in word.indices.reversed()) {
            val char = word[index]
            val nodeOfChar = currNode.getNext(char)
            val nodeNotExist = nodeOfChar == null
            if (nodeNotExist) return

            checkNotNull(nodeOfChar)
            val isPalindromePairs = nodeOfChar.isTermination &&
                    nodeOfChar.wordIndex != wordIndex &&
                    word.isPalindrome(0 until index)
            if (isPalindromePairs) container.add(listOf(nodeOfChar.wordIndex, wordIndex))

            currNode = nodeOfChar
        }
        addPairsWhenComplementOfWordIsLonger(container, wordIndex, currNode)
    }

    private fun addPairsWhenComplementOfWordIsLonger(
        container: MutableList<List<Int>>,
        wordIndex: Int,
        lastMatchedNode: CustomTrieNode,
        accString: StringBuilder = StringBuilder()
    ) {
        for (nodeOfChar in lastMatchedNode.nextNodes) {
            if (nodeOfChar != null) {
                accString.append(nodeOfChar.char)
                val isPalindromePairs = nodeOfChar.isTermination && accString.isPalindrome()
                if (isPalindromePairs) container.add(listOf(nodeOfChar.wordIndex, wordIndex))

                addPairsWhenComplementOfWordIsLonger(container, wordIndex, nodeOfChar, accString)
                accString.deleteCharAt(accString.lastIndex)
            }
        }
    }
}