package com.hj.leetcode.kotlin.problem17

/**
 * LeetCode page: [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/);
 */
class Solution {

    private val digitChars = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )

    /* Complexity:
     * Time O(N * 4^N) and Space O(N * 4^N) where N is the length of digits;
     */
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) {
            return emptyList()
        }

        val result = mutableListOf<String>()
        makeCombination(digits) { combination ->
            result.add(combination)
        }
        return result
    }

    private fun makeCombination(
        digits: String,
        currentLetters: StringBuilder = StringBuilder(),
        onEachCombination: (combination: String) -> Unit
    ) {
        if (currentLetters.length == digits.length) {
            onEachCombination(currentLetters.toString())
            return
        }

        val nextDigit = digits[currentLetters.length]
        for (char in checkNotNull(digitChars[nextDigit])) {
            currentLetters.append(char)
            makeCombination(digits, currentLetters, onEachCombination)
            currentLetters.apply { deleteCharAt(lastIndex) }
        }
    }
}