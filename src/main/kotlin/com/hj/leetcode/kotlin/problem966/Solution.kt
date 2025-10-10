package com.hj.leetcode.kotlin.problem966

/**
 * LeetCode page: [966. Vowel Spellchecker](https://leetcode.com/problems/vowel-spellchecker/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(N) where N and M are the total
    // lengths of the wordlist and queries, respectively.
    fun spellchecker(
        wordlist: Array<String>,
        queries: Array<String>,
    ): Array<String> {
        val words = hashMapOf<String, String>()
        val lcWords = hashMapOf<String, String>()
        val fuzzyLcWords = hashMapOf<String, String>()

        for (word in wordlist) {
            val lcWord = word.lowercase()
            val fuzzyLcWord = lcWord.toFuzzy()

            words.putIfAbsent(word, word)
            lcWords.putIfAbsent(lcWord, word)
            fuzzyLcWords.putIfAbsent(fuzzyLcWord, word)
        }

        return Array(queries.size) {
            val query = queries[it]
            words[query] ?: lcWords[query.lowercase()] ?: fuzzyLcWords[query.lowercase().toFuzzy()] ?: ""
        }
    }

    // Replaces the vowels in word with 'a'.
    private fun String.toFuzzy(): String =
        buildString(this.length) {
            for (c in this@toFuzzy) {
                append(if (isVowel(c)) 'a' else c)
            }
        }

    private fun isVowel(c: Char): Boolean {
        // shr only use the lowest five bits of bitCount
        return (0x10_4111 shr (c - 'A')) and 1 != 0
    }
}
