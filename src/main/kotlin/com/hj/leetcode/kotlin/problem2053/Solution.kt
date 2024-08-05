package com.hj.leetcode.kotlin.problem2053

/**
 * LeetCode page: [2053. Kth Distinct String in an Array](https://leetcode.com/problems/kth-distinct-string-in-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the flattened length of arr;
     */
    fun kthDistinct(arr: Array<String>, k: Int): String {
        val strFrequency = arr.groupingBy { it }.eachCount()
        return arr.asSequence()
            .filter { strFrequency[it] == 1 }
            .drop(k - 1)
            .firstOrNull() ?: ""
    }
}