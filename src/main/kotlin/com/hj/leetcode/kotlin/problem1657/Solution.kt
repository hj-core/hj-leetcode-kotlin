package com.hj.leetcode.kotlin.problem1657

/**
 * LeetCode page: [1657. Determine if Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close/);
 */
class Solution {
    /* Complexity:
     * Time O(|word1|) & Space O(1);
     */
    fun closeStrings(word1: String, word2: String): Boolean {
        /* Despite the operations, there are three invariants of the state:
         *   1. the length of string;
         *   2. the kinds of lowercase;
         *   3. the group sizes by kinds, e.g. when 'aacabb' is transformed to 'bbcbaa', there are still
         *      lowercase appear one, two and three times respectively;
         */
        val hasSameLength = word1.length == word2.length
        if (!hasSameLength) return false

        val counter1 = countEachLowercase(word1)
        val counter2 = countEachLowercase(word2)

        return hasSameKindsOfLowercase(counter1, counter2) && hasSameGroupSizesByKinds(counter1, counter2)
    }

    private fun countEachLowercase(lowercaseOnly: String): IntArray {
        val countPerLowercase = IntArray(26)
        for (char in lowercaseOnly) {
            countPerLowercase[char - 'a']++
        }
        return countPerLowercase
    }

    private fun hasSameKindsOfLowercase(counter1: IntArray, counter2: IntArray): Boolean {
        return counter1.indices.all { index ->
            (counter1[index] == 0) == (counter2[index] == 0)
        }
    }

    private fun hasSameGroupSizesByKinds(counter1: IntArray, counter2: IntArray): Boolean {
        val sortedSize1 = counter1.sorted()
        val sortedSize2 = counter2.sorted()
        return sortedSize1 == sortedSize2
    }
}