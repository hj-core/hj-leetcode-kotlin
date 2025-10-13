package com.hj.leetcode.kotlin.problem2273

/**
 * LeetCode page: [2273. Find Resultant Array After Removing Anagrams](https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/);
 */
class Solution {
    // Complexity:
    // Time O(L) and Space O(L) where L is the flattened length
    // of words.
    fun removeAnagrams(words: Array<String>): List<String> {
        val result = mutableListOf(words[0])
        val hash = hashAnagram(words[0])

        for (i in 1..<words.size) {
            val newHash = hashAnagram(words[i])
            if (!newHash.contentEquals(hash)) {
                result.add(words[i])
                newHash.copyInto(hash)
            }
        }
        return result
    }

    // Returns a collision-free hash for checking anagrams
    // consisting of up to 15 lowercase English letters.
    fun hashAnagram(word: String): LongArray {
        val result = LongArray(2)
        for (c in word) {
            val idx = c - 'a'
            result[idx and 1] += 1L shl (idx shl 1)
        }
        return result
    }
}
