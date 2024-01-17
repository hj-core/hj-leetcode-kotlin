package com.hj.leetcode.kotlin.problem1207

/**
 * LeetCode page: [1207. Unique Number of Occurrences](https://leetcode.com/problems/unique-number-of-occurrences/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of arr;
     */
    fun uniqueOccurrences(arr: IntArray): Boolean {
        val occurrences = arr
            .asIterable()
            .groupingBy { it }
            .eachCount()
            .values
        return occurrences.isEachElementUnique()
    }

    private fun <T> Collection<T>.isEachElementUnique(): Boolean {
        val visited = hashSetOf<T>()
        for (elem in this) {
            if (!visited.add(elem)) {
                return false
            }
        }
        return true
    }
}