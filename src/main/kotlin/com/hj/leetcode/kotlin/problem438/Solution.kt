package com.hj.leetcode.kotlin.problem438

/**
 * LeetCode page: [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|) and Space O(1);
     */
    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.length < p.length) return emptyList()

        val startIndicesOfAnagrams = mutableListOf<Int>()
        val anagramCharCount = getCountOfEachChar(p)

        val windowCharCount = getCountOfEachChar(s, p.indices)
        var numMatchedChars = (0 until 26).count { windowCharCount[it] == anagramCharCount[it] }
        if (numMatchedChars == 26) startIndicesOfAnagrams.add(0)

        for (index in p.length until s.length) {
            val popCountIndex = s[index - p.length] - 'a'
            windowCharCount[popCountIndex]--
            when (windowCharCount[popCountIndex]) {
                anagramCharCount[popCountIndex] -> numMatchedChars++
                anagramCharCount[popCountIndex] - 1 -> numMatchedChars--
            }

            val pushCountIndex = s[index] - 'a'
            windowCharCount[pushCountIndex]++
            when (windowCharCount[pushCountIndex]) {
                anagramCharCount[pushCountIndex] -> numMatchedChars++
                anagramCharCount[pushCountIndex] + 1 -> numMatchedChars--
            }

            if (numMatchedChars == 26) {
                val windowStartIndex = index - p.length + 1
                startIndicesOfAnagrams.add(windowStartIndex)
            }
        }
        return startIndicesOfAnagrams
    }

    private fun getCountOfEachChar(lowercase: String, indexRange: IntRange = lowercase.indices): IntArray {
        val charCount = IntArray(26)
        for (index in indexRange) {
            charCount[lowercase[index] - 'a']++
        }
        return charCount
    }
}