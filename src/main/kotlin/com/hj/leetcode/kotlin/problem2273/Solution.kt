package com.hj.leetcode.kotlin.problem2273

/**
 * LeetCode page: [2273. Find Resultant Array After Removing Anagrams](https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Aux_Space O(1) where N is the flat length of words;
     */
    fun removeAnagrams(words: Array<String>): List<String> {
        val ans = mutableListOf(words[0])
        var prevCharFrequency = getCharFrequency(words[0])
        for (index in 1..words.lastIndex) {
            val currCharFrequency = getCharFrequency(words[index])
            if (!currCharFrequency.contentEquals(prevCharFrequency)) {
                ans.add(words[index])
                prevCharFrequency = currCharFrequency
            }
        }
        return ans
    }

    private fun getCharFrequency(lowercaseString: String): IntArray {
        val charFrequency = IntArray(26)
        for (char in lowercaseString) {
            charFrequency[char - 'a']++
        }
        return charFrequency
    }
}