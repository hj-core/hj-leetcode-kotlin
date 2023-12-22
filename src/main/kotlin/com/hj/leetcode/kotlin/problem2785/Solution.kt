package com.hj.leetcode.kotlin.problem2785

/**
 * LeetCode page: [2785. Sort Vowels in a String](https://leetcode.com/problems/sort-vowels-in-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun sortVowels(s: String): String {
        val vowels = charArrayOf('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u')
        val vowelsCount = IntArray(vowels.size) { index ->
            s.count { char -> char == vowels[index] }
        }

        val result = StringBuilder(s)
        var vowelIndex = 0

        for ((index, char) in result.withIndex()) {
            if (char in vowels) {
                while (vowelsCount[vowelIndex] == 0) {
                    vowelIndex++
                }
                result[index] = vowels[vowelIndex]
                vowelsCount[vowelIndex]--
            }
        }
        return result.toString()
    }
}