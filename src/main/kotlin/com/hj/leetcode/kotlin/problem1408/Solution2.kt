package com.hj.leetcode.kotlin.problem1408

class Solution2 {
    /* Complexity:
     * Time O(MN) and Space O(M)
     * where N is the length of words and M is the flattened length of words.
     */
    fun stringMatching(words: Array<String>): List<String> {
        val result = mutableListOf<String>()
        for (i in words.indices) {
            val matcher = KMP(words[i])
            for (j in words.indices) {
                if (j != i && matcher.isSubStringOf(words[j])) {
                    result.add(words[i])
                    break
                }
            }
        }
        return result
    }

    private class KMP(
        val pattern: String,
    ) {
        // table[i]::=
        // the longest prefix length of pattern[0..<i] that is a suffix of pattern[0..=i]
        private val table = queryTable(pattern)

        private fun queryTable(pattern: String): IntArray {
            val result = IntArray(pattern.length)
            for (i in 1..<pattern.length) {
                var k = result[i - 1]
                while (0 < k && pattern[i] != pattern[k]) {
                    k = result[k - 1]
                }
                if (pattern[k] == pattern[i]) {
                    result[i] = k + 1
                }
            }
            return result
        }

        fun isSubStringOf(s: String): Boolean {
            if (s.length < table.size) {
                return false
            }
            var k = 0
            for (i in s.indices) {
                while (0 < k && s[i] != pattern[k]) {
                    k = table[k - 1]
                }
                if (s[i] == pattern[k]) {
                    k++
                }
                if (k == pattern.length) {
                    return true
                }
            }
            return false
        }
    }
}
