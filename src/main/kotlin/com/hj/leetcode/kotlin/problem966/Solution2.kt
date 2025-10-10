package com.hj.leetcode.kotlin.problem966

/**
 * LeetCode page: [966. Vowel Spellchecker](https://leetcode.com/problems/vowel-spellchecker/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+M) and Space O(N) where N and M are the total
    // lengths of the wordlist and queries, respectively.
    fun spellchecker(
        wordlist: Array<String>,
        queries: Array<String>,
    ): Array<String> {
        val words = hashMapOf<Long, String>()
        val lcWords = hashMapOf<Long, String>()
        val fuzzyLcWords = hashMapOf<Long, String>()

        for (word in wordlist) {
            val (exactHash, lcHash, fuzzyLcHash) = calcHashes(word)
            words.putIfAbsent(exactHash, word)
            lcWords.putIfAbsent(lcHash, word)
            fuzzyLcWords.putIfAbsent(fuzzyLcHash, word)
        }

        return Array(queries.size) {
            val (exactHash, lcHash, fuzzyLcHash) = calcHashes(queries[it])
            words[exactHash] ?: lcWords[lcHash] ?: fuzzyLcWords[fuzzyLcHash] ?: ""
        }
    }

    // Returns the hashes for exact matching, lowercase matching
    // and fuzzy-lowercase matching. It is collision-free but only
    // works under the tight constraints of this problem.
    private fun calcHashes(word: String): LongArray {
        var exactHash = 0L
        var lcHash = 0L
        var fuzzyLcHash = 0L // lowercase and replace all vowels with 'A'

        for ((i, c) in word.withIndex()) {
            val idx = (c - 'A').toLong()
            val bits = idx shl (i shl 3)
            val lcBits = (idx and 32.inv()) shl (i shl 3)

            exactHash = exactHash xor bits
            lcHash = lcHash xor lcBits

            val isNotVowel = (0x10_4111 shr idx.toInt()) and 1 == 0
            if (isNotVowel) {
                fuzzyLcHash = fuzzyLcHash xor lcBits
            }
        }
        return longArrayOf(exactHash, lcHash, fuzzyLcHash)
    }
}
