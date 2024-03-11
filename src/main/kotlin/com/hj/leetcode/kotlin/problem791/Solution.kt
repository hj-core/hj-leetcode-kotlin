package com.hj.leetcode.kotlin.problem791

/**
 * LeetCode page: [791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(N) where M is the length of order and
     * N is the length of s;
     */
    fun customSortString(order: String, s: String): String {
        val charFreq = s.groupingBy { it }.eachCountTo(hashMapOf())
        val result = StringBuilder(s.length)

        for (char in order) {
            charFreq[char]?.let {
                repeat(it) { result.append(char) }
                charFreq.remove(char)
            }
        }
        for ((char, count) in charFreq) {
            repeat(count) { result.append(char) }
        }
        return result.toString()
    }
}