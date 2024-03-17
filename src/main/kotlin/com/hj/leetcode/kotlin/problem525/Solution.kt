package com.hj.leetcode.kotlin.problem525

/**
 * LeetCode page: [525. Contiguous Array](https://leetcode.com/problems/contiguous-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun findMaxLength(nums: IntArray): Int {
        var result = 0
        var prefixDiff = 0 // the number of 1's minus the number of 0's
        val firstIndex = hashMapOf(0 to -1) // the index where this prefixDiff first occurred

        for ((i, num) in nums.withIndex()) {
            prefixDiff += if (num == 0) -1 else 1
            val first = firstIndex.computeIfAbsent(prefixDiff) { i }
            val length = i - first
            if (length > result) {
                result = length
            }
        }
        return result
    }
}