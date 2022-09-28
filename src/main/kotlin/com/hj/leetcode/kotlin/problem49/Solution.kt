package com.hj.leetcode.kotlin.problem49

/**
 * LeetCode page: [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/);
 */
class Solution {
    /* Complexity:
     * Time O(K) and Space O(K) where K is the flat length of strs;
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val groups = groupByCharFrequency(strs)
        return groups.values.toList()
    }

    private fun groupByCharFrequency(strings: Array<String>): Map<List<Int>, List<String>> {
        val groups = hashMapOf<List<Int>, MutableList<String>>()

        for (string in strings) {
            val charFrequency = countCharFrequency(string)
            groups
                .getOrPut(charFrequency) { mutableListOf() }
                .add(string)
        }
        return groups
    }

    private fun countCharFrequency(lowercase: String): List<Int> {
        val frequency = MutableList(26) { 0 }
        for (char in lowercase) {
            frequency[char - 'a']++
        }
        return frequency
    }
}