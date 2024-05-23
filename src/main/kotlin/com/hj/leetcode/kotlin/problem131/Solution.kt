package com.hj.leetcode.kotlin.problem131

/**
 * LeetCode page: [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/);
 */
class Solution {
    /* Complexity:
     * Time O(N * 2^N) and Space O(N * 2^N) where N is the length of s;
     */
    fun partition(s: String): List<List<String>> = buildList {
        dfsAllPalindromePartitions(s, 0, mutableListOf()) {
            add(it)
        }
    }

    private fun dfsAllPalindromePartitions(
        s: String,
        index: Int,
        breakPoints: MutableList<Int>,
        onEachPartition: (partition: List<String>) -> Unit,
    ) {
        if (index == s.length) {
            if (isPalindrome(s, breakPoints.last(), s.length)) {
                onEachPartition(partition(s, breakPoints))
            }
            return
        }

        if (breakPoints.isNotEmpty() && isPalindrome(s, breakPoints.last(), index)) {
            breakPoints.add(index)
            dfsAllPalindromePartitions(s, index + 1, breakPoints, onEachPartition)
            breakPoints.removeLast()
        }
        if (breakPoints.isEmpty()) {
            breakPoints.add(index)
        }
        dfsAllPalindromePartitions(s, index + 1, breakPoints, onEachPartition)
    }

    private fun isPalindrome(s: String, start: Int, endExclusive: Int): Boolean {
        var left = start
        var right = endExclusive - 1
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
            val endExclusive = breakPoints.getOrElse(i + 1) { s.length }
            add(s.substring(start, endExclusive))
        }
    }
}