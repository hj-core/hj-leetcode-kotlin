package com.hj.leetcode.kotlin.problem3042

/**
 * LeetCode page: [3042. Count Prefix and Suffix Pairs I](https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2 L) and Space O(1)
     * where N is the length of words and L is the longest length among words.
     */
    fun countPrefixSuffixPairs(words: Array<String>): Int {
        var result = 0
        for (i in words.indices) {
            for (j in i + 1..<words.size) {
                if (words[j].startsWith(words[i]) && words[j].endsWith(words[i])) {
                    result++
                }
            }
        }
        return result
    }
}
