package com.hj.leetcode.kotlin.problem1657

/**
 * LeetCode page: [1657. Determine if Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close/);
 */
class Solution {
    /* Complexity:
     * Time O(|word1|) and Space O(1);
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

        val count1 = countEachLowercase(word1)
        val count2 = countEachLowercase(word2)

        return hasSameKindsOfLowercase(count1, count2) && hasSameGroupSizesByKinds(count1, count2)
    }

    private fun countEachLowercase(lowercaseOnly: String): IntArray {
        val countPerLowercase = IntArray(26)
        for (char in lowercaseOnly) {
            countPerLowercase[char - 'a']++
        }
        return countPerLowercase
    }

    private fun hasSameKindsOfLowercase(countPerLowercase1: IntArray, countPerLowercase2: IntArray): Boolean {
        return countPerLowercase1.indices.all { index ->
            (countPerLowercase1[index] == 0) == (countPerLowercase2[index] == 0)
        }
    }

    private fun hasSameGroupSizesByKinds(countPerLowercase1: IntArray, countPerLowercase2: IntArray): Boolean {
        val sortedSizes1 = countPerLowercase1.sorted()
        val sortedSizes2 = countPerLowercase2.sorted()
        return sortedSizes1 == sortedSizes2
    }
}