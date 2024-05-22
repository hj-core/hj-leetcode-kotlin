package com.hj.leetcode.kotlin.problem131

/**
 * LeetCode page: [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/);
 */
class Solution {
    /* Complexity:
     * Time O(N * 2^N) and Space O(N * 2^N) where N is the length of s;
     */
    fun partition(s: String): List<List<String>> = buildList {
        dfsAllPalindromePartition(s, 0, mutableListOf()) {
            add(it)
        }
    }

    private fun dfsAllPalindromePartition(
        s: String,
        index: Int,
        currentBreakPoints: MutableList<Int>,
        onEachPartition: (partitioning: List<String>) -> Unit,
    ) {
        if (index == s.length) {
            if (isPalindrome(s, currentBreakPoints.last(), s.length)) {
                onEachPartition(partition(s, currentBreakPoints))
            }
            return
        }

        if (currentBreakPoints.isNotEmpty() && isPalindrome(s, currentBreakPoints.last(), index)) {
            currentBreakPoints.add(index)
            dfsAllPalindromePartition(s, index + 1, currentBreakPoints, onEachPartition)
            currentBreakPoints.removeLast()
        }
        if (currentBreakPoints.isEmpty()) {
            currentBreakPoints.add(index)
        }
        dfsAllPalindromePartition(s, index + 1, currentBreakPoints, onEachPartition)
    }

    private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
        var left = start
        var right = end - 1
        while (left < right) {
            if (s[left] != s[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }

    private fun partition(s: String, breakPoints: List<Int>): List<String> = buildList {
        for (i in breakPoints.indices) {
            val start = breakPoints[i]
            val end = breakPoints.getOrElse(i + 1) { s.length }
            add(s.substring(start, end))
        }
    }
}