package com.hj.leetcode.kotlin.problem451

/**
 * LeetCode page: [451. Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|) and Space O(1);
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