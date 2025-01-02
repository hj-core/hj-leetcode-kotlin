package com.hj.leetcode.kotlin.problem2559

/**
 * LeetCode page: [2559. Count Vowel Strings in Ranges](https://leetcode.com/problems/count-vowel-strings-in-ranges/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Auxiliary Space O(M)
     * where M and N are the length of words and queries, respectively.
     */
    fun vowelStrings(
        words: Array<String>,
        queries: Array<IntArray>,
    ): IntArray {
        // countPrefix[i]::= the number of vowel strings in words[0..<i]
        val countPrefix = IntArray(words.size + 1)
        for (i in 1..words.size) {
            countPrefix[i] = countPrefix[i - 1] + if (isVowelString(words[i - 1])) 1 else 0
        }

        return IntArray(queries.size) {
            val (left, right) = queries[it]
            countPrefix[right + 1] - countPrefix[left]
        }
    }

    private fun isVowelString(s: String): Boolean = s.first() in "aeiou" && s.last() in "aeiou"
}
