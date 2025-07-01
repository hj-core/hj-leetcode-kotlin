package com.hj.leetcode.kotlin.problem3330

/**
 * LeetCode page: [3330. Find the Original Typed String I](https://leetcode.com/problems/find-the-original-typed-string-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of word.
    fun possibleStringCount(word: String): Int = 1 + (1..<word.length).count { word[it] == word[it - 1] }
}
