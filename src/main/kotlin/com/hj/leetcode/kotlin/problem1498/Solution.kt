package com.hj.leetcode.kotlin.problem1498

/**
 * LeetCode page: [1498. Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun numSubseq(
        nums: IntArray,
        target: Int,
    ): Int {
        val sortedNums = nums.sortedArray()
        if (sortedNums[0] * 2 > target) {
            return 0
        }

        var result = 0
        var left = 0
        var right = partition(sortedNums, target - sortedNums[left]) - 1
        val modulo = 1_000_000_007
        val shlMod = computeShlModTable(right, modulo)

        while (left <= right) {
            if (sortedNums[left] + sortedNums[right] > target) {
                right--
            } else {
                result = (result + shlMod[right - left]) % modulo
                left++
            }
        }
        return result
    }

    // Returns the number of elements in sortedNums that are not greater
    // than threshold.
    private fun partition(
        sortedNums: IntArray,
        threshold: Int,
    ): Int {
        // Our result is in the range [left, right]
        var left = 0
        var right = sortedNums.size

        while (left < right) {
            val mid = left + (right - left) / 2
            if (sortedNums[mid] <= threshold) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }

    // Returns a lookup table where the value at index i equals 2^i % modulo.
    private fun computeShlModTable(
        maxExp: Int,
        modulo: Int,
    ): IntArray {
        val result = IntArray(maxExp + 1)
        result[0] = 1
        for (i in 1..<result.size) {
            result[i] = (result[i - 1] * 2) % modulo
        }
        return result
    }
}
