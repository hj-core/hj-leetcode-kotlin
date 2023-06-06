package com.hj.leetcode.kotlin.problem1502

/**
 * LeetCode page: [1502. Can Make Arithmetic Progression From Sequence](https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of arr;
     */
    fun canMakeArithmeticProgression(arr: IntArray): Boolean {
        val min = arr.min()!!
        val max = arr.max()!!

        val isNonIntCommonDifference = (max - min) % (arr.size - 1) != 0
        if (isNonIntCommonDifference) {
            return false
        }

        val commonDifference = (max - min) / (arr.size - 1)
        if (commonDifference == 0) {
            return true
        }

        return arr.all { (it - min) % commonDifference == 0 } && arr.noDuplicates()
    }

    private fun IntArray.noDuplicates(): Boolean {
        return this.toHashSet().size == this.size
    }
}