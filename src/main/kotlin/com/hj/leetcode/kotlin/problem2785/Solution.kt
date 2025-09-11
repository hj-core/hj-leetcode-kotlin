package com.hj.leetcode.kotlin.problem2785

/**
 * LeetCode page: [2785. Sort Vowels in a String](https://leetcode.com/problems/sort-vowels-in-a-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Auxiliary Space O(1) where N is the length
    // of s. We treat the number of different vowels (i.e., 1) as
    // a constant.
    fun sortVowels(s: String): String {
        val vowels = charArrayOf('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u')

        val cntVowels = IntArray(vowels.size)
        for (c in s) {
            val i = vowels.indexOf(c)
            if (i >= 0) {
                cntVowels[i]++
            }
        }

        val result = StringBuilder(s.length)
        var j = 0 // index of vowels
        for (c in s) {
            if (c in vowels) {
                while (cntVowels[j] == 0) {
                    j++
                }
                result.append(vowels[j])
                cntVowels[j]--
            } else {
                result.append(c)
            }
        }
        return result.toString()
    }
}
