package com.hj.leetcode.kotlin.problem1207

/**
 * LeetCode page: [1207. Unique Number of Occurrences](https://leetcode.com/problems/unique-number-of-occurrences/);
 */
class Solution {
    /* Complexity:
     * Time O(|arr|) and Space O(|arr|);
     */
    fun uniqueOccurrences(arr: IntArray): Boolean {
        val countPerNum = countEachNum(arr)
        val counts = countPerNum.values
        return counts.isEachElementUnique()
    }

    private fun countEachNum(numbers: IntArray): Map<Int, Int> {
        val countPerNum = hashMapOf<Int, Int>()
        for (num in numbers) {
            countPerNum.let {
                it[num] = it.getOrDefault(num, 0) + 1
            }
        }
        return countPerNum
    }

    private fun <T> Collection<T>.isEachElementUnique(): Boolean {
        val visited = hashSetOf<T>()
        for (elem in this) {
            if (!visited.add(elem)) return false
        }
        return true
    }
}