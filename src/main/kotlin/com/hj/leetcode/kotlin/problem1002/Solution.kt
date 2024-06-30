package com.hj.leetcode.kotlin.problem1002

import kotlin.math.min

/**
 * LeetCode page: [1002. Find Common Characters](https://leetcode.com/problems/find-common-characters/);
 */
class Solution {
    /* Complexity:
     * Time O(M+NL) and Space O(L) where M is the flattened length of words,
     * N is the size of words, and L is the length of shortest word;
     */
    fun commonChars(words: Array<String>): List<String> {
        val resultCount = countCharsOfShortestWord(words)
        for (word in words) {
            if (resultCount.isEmpty()) {
                return emptyList()
            }
            val commonCount = countSpecificChars(word, resultCount.keys)
            val resultCountIterator = resultCount.iterator()
            for ((char, count) in resultCountIterator) {
                if (char !in commonCount) {
                    resultCountIterator.remove()
                } else {
                    resultCount[char] = min(count, checkNotNull(commonCount[char]))
                }
            }
        }
        return buildResult(resultCount)
    }

    private fun countCharsOfShortestWord(words: Array<String>) = (words
        .minByOrNull { it.length }
        ?.groupingBy { it }
        ?.eachCountTo(hashMapOf())
        ?: hashMapOf())

    private fun countSpecificChars(word: String, targetChars: Set<Char>): Map<Char, Int> {
        val result = hashMapOf<Char, Int>()
        for (char in word) {
            if (char in targetChars) {
                result.compute(char) { _, count -> if (count == null) 1 else count + 1 }
            }
        }
        return result
    }

    private fun buildResult(charCount: HashMap<Char, Int>): List<String> = buildList {
        for ((char, count) in charCount) {
            repeat(count) { add(char.toString()) }
        }
    }
}