package com.hj.leetcode.kotlin.problem1684

/**
 * LeetCode page: [1684. Count the Number of Consistent Strings](https://leetcode.com/problems/count-the-number-of-consistent-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N) where N is the length of allowed
     * and M is the flattened length of words.
     */
    fun countConsistentStrings(
        allowed: String,
        words: Array<String>,
    ): Int {
        val allowedSet = allowed.toSet()
        return words.count { word -> word.all { it in allowedSet } }
    }
}
