package com.hj.leetcode.kotlin.problem345

/**
 * LeetCode page: [345. Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the length of s;
     */
    fun reverseVowels(s: String): String {
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

        val builder = StringBuilder(s)
        var leftIndex = 0
        var rightIndex = builder.lastIndex

        while (leftIndex < rightIndex) {
            while (leftIndex < rightIndex && s[leftIndex] !in vowels) {
                leftIndex++
            }

            while (rightIndex > leftIndex && s[rightIndex] !in vowels) {
                rightIndex--
            }

            builder[leftIndex] = s[rightIndex]
            builder[rightIndex] = s[leftIndex]

            leftIndex++
            rightIndex--
        }

        return builder.toString()
    }
}