package com.hj.leetcode.kotlin.problem3306

/**
 * LeetCode page: [3306. Count of Substrings Containing Every Vowel and K Consonants II](https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of the `word`.
    // Here, we treat the size of vowels as a constant.
    fun countOfSubstrings(
        word: String,
        k: Int,
    ): Long {
        var result = 0L
        var minRight = 0
        var maxRight = 0
        val counter = Counter()

        // Our window is the substring of word from left to right(exclusive)
        for (left in 0..word.length - 5) {
            if (0 < left) {
                counter.remove(word[left - 1])
            }
            while (minRight < word.length && !counter.containAllVowels()) {
                counter.add(word[minRight])
                minRight++
            }
            while (minRight < word.length && counter.consonantCnt() < k) {
                counter.add(word[minRight])
                minRight++
            }
            if (minRight == word.length && (!counter.containAllVowels() || counter.consonantCnt() < k)) {
                break
            }
            // If our code reaches here,
            // the substring word[left..<minRight] contains all the vowels and at least k consonants.

            if (k < counter.consonantCnt()) {
                continue
            }
            if (maxRight < minRight) {
                maxRight = minRight
                while (maxRight < word.length && word[maxRight] in counter.vowels) {
                    maxRight++
                }
            }
            result += maxRight - minRight + 1
        }
        return result
    }

    private class Counter {
        val vowels = "aeiou"
        private val frequencies = IntArray(6) // The frequencies of [consonants, 'a', 'e', 'i', 'o', 'u']

        fun add(c: Char) {
            frequencies[vowels.indexOf(c) + 1]++
        }

        fun remove(c: Char) {
            frequencies[vowels.indexOf(c) + 1]--
        }

        fun containAllVowels(): Boolean = (1..<frequencies.size).all { frequencies[it] > 0 }

        fun consonantCnt(): Int = frequencies[0]
    }
}
