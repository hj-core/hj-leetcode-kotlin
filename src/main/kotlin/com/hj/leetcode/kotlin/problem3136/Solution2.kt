package com.hj.leetcode.kotlin.problem3136

/**
 * LeetCode page: [3136. Valid Word](https://leetcode.com/problems/valid-word/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of word.
    fun isValid(word: String): Boolean = PATTERN.matches(word)

    companion object {
        private val PATTERN =
            Regex("""^(?i)(?=.{3,}$)(?=.*[aeiou])(?=.*[b-df-h-j-np-tv-z])[a-z\d]+$""")
    }
}
