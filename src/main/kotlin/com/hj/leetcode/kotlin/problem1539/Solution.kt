package com.hj.leetcode.kotlin.problem1539

/**
 * LeetCode page: [1539. Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N is the size of arr;
     */
    fun findKthPositive(arr: IntArray, k: Int): Int {
        return k + firstIndexMissingAtLeast(k, arr)
    }

    private fun firstIndexMissingAtLeast(numberOfMissing: Int, sortedUniquePositives: IntArray): Int {
        var left = 0
        var right = sortedUniquePositives.lastIndex
        while (left <= right) {
            val mid = (left + right) ushr 1
            val midValue =  sortedUniquePositives[mid]
            val numMissingAtMid = midValue - (mid + 1)
            if (numMissingAtMid >= numberOfMissing) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }
}