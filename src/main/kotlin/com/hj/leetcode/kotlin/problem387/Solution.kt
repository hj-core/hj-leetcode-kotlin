package com.hj.leetcode.kotlin.problem387

/**
 * LeetCode page: [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun firstUniqChar(s: String): Int {
        val count = s.groupingBy { it }.eachCount()
        return s.indexOfFirst { count[it] == 1 }
    }
}