package com.hj.leetcode.kotlin.problem451

import java.util.*

/**
 * LeetCode page: [451. Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|) and Space O(1);
     */
    fun frequencySort(s: String): String {
        val countPerChar = countEachChar(s)
        val sortedChars = groupByCountAndSortDescending(countPerChar)
        return buildSortedString(sortedChars)
    }

    private fun countEachChar(s: String): Map<Char, Int> {
        return s
            .groupingBy { char -> char }
            .eachCount()
    }

    private fun groupByCountAndSortDescending(countPerChar: Map<Char, Int>): Map<Int, List<Char>> {
        val sortedGroups = TreeMap<Int, MutableList<Char>>(reverseOrder())
        for ((char, charCount) in countPerChar) {
            sortedGroups.putIfAbsent(charCount, mutableListOf())
            sortedGroups[charCount]!!.add(char)
        }
        return sortedGroups
    }

    private fun buildSortedString(charsSortedByCount: Map<Int, List<Char>>): String {
        val builder = StringBuilder()
        for ((count, chars) in charsSortedByCount) {
            for (char in chars) {
                repeat(count) { builder.append(char) }
            }
        }
        return builder.toString()
    }
}