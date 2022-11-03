package com.hj.leetcode.kotlin.problem2131

/**
 * LeetCode page: [2131. Longest Palindrome by Concatenating Two Letter Words](https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of words. Space complexity is O(1) because there are
     * at most 26^2 different words;
     */
    fun longestPalindrome(words: Array<String>): Int {
        val countPerWord = countEachWord(words)
        return findMaxLength(countPerWord)
    }

    private fun countEachWord(words: Array<String>): MutableMap<String, Int> {
        val counts = hashMapOf<String, Int>()

        for (word in words) {
            counts[word] = counts.getOrDefault(word, 0) + 1
        }
        return counts
    }

    private fun findMaxLength(countPerWord: MutableMap<String, Int>): Int {
        var halfMaxLength = 0
        var hasOddCountTwin = false
        val countsIterator = countPerWord.iterator()

        fun updateWhenWordIsTwin(count: Int) {
            if (count.isEven()) {
                halfMaxLength += count
            } else {
                halfMaxLength += count - 1
                hasOddCountTwin = true
            }
            countsIterator.remove()
        }

        fun updateWhenWordIsNotTwin(word: String, count: Int) {
            val reversed = word.reversed()
            val countOfReversed = countPerWord[reversed]
            if (countOfReversed != null) {
                halfMaxLength += minOf(count, countOfReversed) * 2
            }
            countsIterator.remove()
        }

        while (countsIterator.hasNext()) {
            val (word, count) = countsIterator.next()
            val isTwin = isTwin(word)
            if (isTwin) updateWhenWordIsTwin(count) else updateWhenWordIsNotTwin(word, count)
        }

        if (hasOddCountTwin) halfMaxLength += 1

        return halfMaxLength * 2
    }

    private fun Int.isEven() = this and 1 == 0

    private fun isTwin(word: String) = word.length == 2 && word[0] == word[1]
}