package com.hj.leetcode.kotlin.problem1356

/**
 * LeetCode page: [1356. Sort Integers by The Number of 1 Bits](https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of arr;
     */
    fun sortByBits(arr: IntArray): IntArray {
        return arr
            .sortedWith(compareBy({ it.countOneBits() }, { it }))
            .toIntArray()
    }
}