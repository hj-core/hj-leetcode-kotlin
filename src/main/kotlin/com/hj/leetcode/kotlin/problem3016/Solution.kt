package com.hj.leetcode.kotlin.problem3016

/**
 * LeetCode page: [3016. Minimum Number of Pushes to Type Word II](https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of word;
     */
    fun minimumPushes(word: String): Int {
        val countChar = MutableList(26) { 0 }
        for (c in word) {
            countChar[c - 'a']++
        }

        val pushes = buildList {
            repeat(8) { add(1) }
            repeat(8) { add(2) }
            repeat(8) { add(3) }
            repeat(2) { add(4) }
        }

        countChar.sortBy { -it }
        return (0..<26).sumOf { countChar[it] * pushes[it] }
    }
}