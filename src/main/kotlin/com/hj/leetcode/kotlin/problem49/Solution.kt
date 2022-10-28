package com.hj.leetcode.kotlin.problem49

/**
 * LeetCode page: [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the flat length of strs;
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs
            .groupBy { str -> getCountPerLowercase(str) }
            .map { it.value }
    }

    private fun getCountPerLowercase(lowercaseOnly: String): List<Int> {
        val counts = MutableList(26) { 0 }

        for (char in lowercaseOnly) {
            counts[char - 'a']++
        }

        return counts
    }
}