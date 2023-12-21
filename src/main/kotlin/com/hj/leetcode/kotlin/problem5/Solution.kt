package com.hj.leetcode.kotlin.problem5

/**
 * LeetCode page: [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/description/);
 *
 * Note: There is a linear time complexity algorithm called Manacher's algorithm, see [Ref.](https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm)
 */
class Solution {
    /* Complexity:
     * Time O(|s|^2) and Space O(1);
     */
    fun longestPalindrome(s: String): String {
        var indicesOfLongestPalindrome = 0 until 0
        for (index in s.indices) {
            val longestExpansion = findLongestPalindromeExpansion(index, s)
            if (longestExpansion.size() > indicesOfLongestPalindrome.size()) {
                indicesOfLongestPalindrome = longestExpansion
            }
        }
        return s.slice(indicesOfLongestPalindrome)
    }

    private fun findLongestPalindromeExpansion(seedIndex: Int, s: String): IntRange {
        /* Two cases should be considered: one for odd length palindrome (e.g. "b(a)b") and one for
         * even length palindrome(e.g. "ba(a)b").
         */
        val oddLongest = findLongestPalindromeExpansion(seedIndex, seedIndex, s)
        val evenLongest = findLongestPalindromeExpansion(seedIndex - 1, seedIndex, s)
        return if (oddLongest.size() > evenLongest.size()) oddLongest else evenLongest
    }

    private fun findLongestPalindromeExpansion(floorCenterIndex: Int, ceilingCenterIndex: Int, s: String): IntRange {
        var start = floorCenterIndex
        var end = ceilingCenterIndex
        while (start > -1 && end < s.length && s[start] == s[end]) {
            start--
            end++
        }
        val noValidExpansion = end == ceilingCenterIndex
        return if (noValidExpansion) end until end else start + 1 until end
    }

    private fun IntRange.size() = last - first + 1
}