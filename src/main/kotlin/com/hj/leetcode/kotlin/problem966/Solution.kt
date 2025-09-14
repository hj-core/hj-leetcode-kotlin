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
        val words = hashMapOf<String, Int>()
        val lcWords = hashMapOf<String, Int>()
        val fuzzyLcWords = hashMapOf<String, Int>()

        for ((i, word) in wordlist.withIndex()) {
            if (word !in words) {
                words[word] = i
            }

            val lcWord = word.lowercase()
            if (lcWord !in lcWords) {
                lcWords[lcWord] = i
            }

            val fuzzyLcWord = lcWord.toFuzzy()
            if (fuzzyLcWord !in fuzzyLcWords) {
                fuzzyLcWords[fuzzyLcWord] = i
            }
        }

        return Array(queries.size) {
            val query = queries[it]
            val j = words[query] ?: lcWords[query.lowercase()] ?: fuzzyLcWords[query.lowercase().toFuzzy()] ?: -1
            if (j == -1) "" else wordlist[j]
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
