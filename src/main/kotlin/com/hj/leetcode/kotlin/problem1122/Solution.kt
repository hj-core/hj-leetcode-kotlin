package com.hj.leetcode.kotlin.problem1122

/**
 * LeetCode page: [1122. Relative Sort Array](https://leetcode.com/problems/relative-sort-array/);
 */
class Solution {
    /* Complexity:
     * Time O(M+NLogN) and Space O(M+N) where N is the size of arr1
     * and M is the size of arr2;
     */
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        val valueIndices = arr2
            .indices
            .associateBy { i -> arr2[i] }

        val comparator = compareBy<Int> { num ->
            valueIndices[num] ?: arr2.size
        }.thenComparing { num ->
            num
        }

        return arr1
            .sortedWith(comparator)
            .toIntArray()
    }
}