package com.hj.leetcode.kotlin.problem451

/**
 * LeetCode page: [451. Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/);
 */
class Solution {
    /* Complexity:
     * Time O(N+MLogM) and Space O(N+M) where N is the length of s and
     * M is the size of char set;
     */
    fun frequencySort(s: String): String {
        val countPerChar = s.groupingBy { it }.eachCount()
        val sortedEntries = countPerChar.entries.sortedBy { (_, count) -> -count }
        val builder = StringBuilder()

        for ((char, count) in sortedEntries) {
            repeat(count) { builder.append(char) }
        }
        return builder.toString()
    }
}