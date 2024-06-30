package com.hj.leetcode.kotlin.problem49

/**
 * LeetCode page: [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the flattened length of strs;
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs
            .groupBy { it.charCounts() }
            .values
            .toList()
    }

    private fun String.charCounts(): Map<Char, Int> {
        return this.groupingBy { it }.eachCount()
    }
}